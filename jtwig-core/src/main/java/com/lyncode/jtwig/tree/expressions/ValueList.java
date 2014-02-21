/**
 * Copyright 2012 Lyncode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyncode.jtwig.tree.expressions;

import com.lyncode.jtwig.JtwigContext;
import com.lyncode.jtwig.exception.CalculateException;
import com.lyncode.jtwig.exception.ParseBypassException;
import com.lyncode.jtwig.exception.ParseException;
import com.lyncode.jtwig.tree.api.Expression;

import java.util.ArrayList;
import java.util.List;

public class ValueList implements Expression {

    public static ValueList create(Constant init, Constant end) {
        ValueList valueList = new ValueList();
        if (init.isInstanceOf(Integer.class)) {
            if (!end.isInstanceOf(Integer.class))
                throw new ParseBypassException(new ParseException("Expected an integer for the end of the comprehension list"));
            int initValue = (int) init.as(Integer.class);
            int endValue = (int) end.as(Integer.class);

            if (initValue < endValue) {
                for (int i = initValue; i <= endValue; i++)
                    valueList.add(new Constant<>(i));
            } else {
                for (int i = endValue; i >= initValue; i--)
                    valueList.add(new Constant<>(i));
            }

        } else if (init.isInstanceOf(Character.class)) {
            if (!end.isInstanceOf(Character.class))
                throw new ParseBypassException(new ParseException("Expected a character for the end of the comprehension list"));

            char initValue = (char) init.as(Character.class);
            char endValue = (char) end.as(Character.class);

            if (initValue < endValue) {
                for (char i = initValue; i <= endValue; i++)
                    valueList.add(new Constant<>(i));
            } else {
                for (char i = endValue; i >= initValue; i--)
                    valueList.add(new Constant<>(i));
            }
        } else
            throw new ParseBypassException(new ParseException("Only integers and characters are allowed in comprehension lists"));
        return valueList;
    }

    private List<Expression> values = new ArrayList<>();

    public boolean add(Expression element) {
        this.values.add(element);
        return true;
    }


    @Override
    public List<Object> calculate(JtwigContext context) throws CalculateException {
        List<Object> result = new ArrayList<Object>();
        for (Expression obj : values)
            result.add(obj.calculate(context));
        return result;
    }

    public Expression first() {
        return values.get(0);
    }

    public Expression get(int index) {
        return values.get(index);
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public int size() {
        return values.size();
    }
}

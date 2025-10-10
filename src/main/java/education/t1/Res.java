package education.t1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Res {
    // суть метода в том, что он смотрит на классе объекта аннотацию
    // и если она есть - то скидывает значения всех полей до состояния
    // указанного в конфиг классе этой аннотации
    public void reset(Object... obj) throws Exception {

        if (obj == null) // ранний выход
            return;

        for (Object o : obj) {

            if (o == null) // защита от случайных null
                continue;

            Class<?> targetClass = o.getClass();
            Default def = targetClass.getAnnotation(Default.class);

            if (def == null) // если не @Default идём дальше
                continue;
            Class<?> defClass = def.value();
            Object defInstance = defClass.getDeclaredConstructor().newInstance();

            List<Field> fields = getAllFields(targetClass); // собираем все поля

            Map<String, Field> cfgByName = new HashMap<>();
            for (Field cf : getAllFields(defClass)) {
                if (Modifier.isStatic(cf.getModifiers())) // из конфига читаем только нестатические
                    continue;

                cf.setAccessible(true); // разрешаем чтение private
                cfgByName.putIfAbsent(cf.getName(), cf);
            }

            for (Field f : fields) {

                int f_mod = f.getModifiers();

                if (Modifier.isStatic(f_mod) || Modifier.isFinal(f_mod))
                    continue;

                f.setAccessible(true);

                // ищем одноимённое поле в конфиге
                Field cf = cfgByName.get(f.getName());
                if (cf == null)
                    continue;

                // проверяем совместимость типов
                boolean isAssignable = wrapper(f.getType()).isAssignableFrom(wrapper(cf.getType()));
                if (!isAssignable)
                    continue;

                // читаем значение из конфига и присваиваем
                Object value = cf.get(defInstance);
                f.set(o, value);

            }
        }
    }

    // Вспомогательные методы
    private static List<Field> getAllFields(Class<?> type) {
        List<Field> all = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            all.addAll(Arrays.asList(c.getDeclaredFields()));
        }

        return all;
    }

    private static Class<?> wrapper(Class<?> t) {
        if (!t.isPrimitive())
            return t;
        if (t == int.class)
            return Integer.class;
        if (t == long.class)
            return Long.class;
        if (t == double.class)
            return Double.class;
        if (t == float.class)
            return Float.class;
        if (t == boolean.class)
            return Boolean.class;
        if (t == char.class)
            return Character.class;
        if (t == byte.class)
            return Byte.class;
        if (t == short.class)
            return Short.class;
        if (t == void.class)
            return Void.class;
        return t;
    }

}

package com.github.hcsp.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;


public class Problem1 {
    static class User {
        private String name;
        private int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    // 编写一个方法，统计"年龄大于等于60的用户中，名字是两个字的用户数量"
    public static int countUsers(List<User> users) {
        return (int) users.stream().filter(u -> u.age >= 60).filter(u -> u.name.length() == 2).count();
    }

    // 编写一个方法，筛选出年龄大于等于60的用户，然后将他们按照年龄从大到小排序，将他们的名字放在一个LinkedList中返回
    public static LinkedList<String> collectNames(List<User> users) {

        //1. 方法1：没有用到Collectors.toCollection
//                List<String> userList = users.stream().filter(u -> u.age >= 60)
//                .sorted(comparing(User::getAge).reversed())
//                .map(u -> u.getName())
//                .collect(Collectors.toList());
//
//        return new LinkedList(userList);


        //2. 方法2： 没有简化成lambda
//        return  users.stream().filter(u -> u.age >= 60)
//                .sorted(comparing(User::getAge).reversed())
//                .map(u -> u.getName())
//                .collect(Collectors.toCollection(new Supplier<LinkedList<String>>() {
//                    @Override
//                    public LinkedList<String> get() {
//                        return new LinkedList<>();
//                    }
//                }));

        //3. 方法3：没有简化成方法引用
//        return  users.stream().filter(u -> u.age >= 60)
//                .sorted(comparing(User::getAge).reversed())
//                .map(u -> u.getName())
//                .collect(Collectors.toCollection(() -> new LinkedList<>()));

        return  users.stream().filter(u -> u.age >= 60)
                .sorted(comparing(User::getAge).reversed())
                .map(User::getName)
                .collect(Collectors.toCollection(LinkedList::new));




    }

    public static void main(String[] args) {
        System.out.println(
                countUsers(
                        Arrays.asList(
                                new User("张三", 60),
                                new User("李四", 61),
                                new User("张三丰", 300),
                                new User("王五", 12))));

        System.out.println(
                collectNames(
                        Arrays.asList(
                                new User("张三", 60),
                                new User("李四", 61),
                                new User("张三丰", 300),
                                new User("王五", 12))));
    }
}

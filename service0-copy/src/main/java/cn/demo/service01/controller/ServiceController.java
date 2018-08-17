package cn.demo.service01.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class ServiceController {

    public static class User {
        private int id;
        private String name;

        private Student student;

        public User() {
        }

        public int getId() {
            return id;
        }

        public User setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public Student getStudent() {
            return student;
        }

        public User setStudent(Student student) {
            this.student = student;
            return this;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", student=" + student +
                    '}';
        }
    }

    public static class Student {
        private String stuName;

        public Student() {
        }

        public String getStuName() {
            return stuName;
        }

        public Student setStuName(String stuName) {
            this.stuName = stuName;
            return this;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "stuName='" + stuName + '\'' +
                    '}';
        }
    }

    /**
     * 用于测试ribbon 重试机制
     */
    int count = 0;

    @GetMapping(value = "user/{userId}/{sleepSec}",produces = "text/plain;charset=UTF-8")
    String user(
            @PathVariable String userId,
            @PathVariable int sleepSec
    ) {
        try {
            System.out.println("hello:" + userId);
            count++;
            TimeUnit.SECONDS.sleep(sleepSec - count);
            return "服务器2：hello:" + userId+",count:"+count;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping(value = "test",produces = "text/plain;charset=UTF-8")
    String post(@RequestBody User user) {
        System.out.println(user.toString());
        return user.toString();
    }

    @PostMapping(value="json")
    @ResponseBody
    User json(@RequestBody User user) {
        System.out.println(user.toString());
        return user;
    }

    @GetMapping(value="v0/service/version")
    String version() {
        return "v0/service/version/";
    }
}
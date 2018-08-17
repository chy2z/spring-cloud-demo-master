package cn.demo.service.controller;

import cn.demo.service.client.Service0Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对外服务接口
 */
@RestController
public class ServiceHystrixController extends AbstractController {

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

    @Autowired
    Service0Client service0Client;

    @GetMapping(value="/test/{sleepSec}",produces="text/plain;charset=UTF-8")
    public String test( @PathVariable int sleepSec) {
        //待用内部服务
        return service0Client.test("leo", sleepSec);
    }

    @GetMapping(value="user",produces="text/plain;charset=UTF-8")
    public String user() {
        Student stuName = new Student()
                .setStuName("stuName");
        User asd = new User()
                .setId(0)
                .setName("黑客")
                .setStudent(stuName);

        System.out.println(asd.toString());

        //待用内部服务
        return service0Client.user(asd);
    }

    @GetMapping(value="json",produces="text/plain;charset=UTF-8")
    public String json() {

        Student stuName = new Student()
                .setStuName("stuName");

        User asd = new User()
                .setId(0)
                .setName("黑客")
                .setStudent(stuName);

        System.out.println(asd.toString());

        User u = service0Client.json(asd);

        return u.toString();
    }

    /**
     * 返回json不存在中文乱码问题
     * @return
     */
    @RequestMapping(value = "json2",method = RequestMethod.POST)
    @ResponseBody
    public User json2() {

        Student stuName = new Student()
                .setStuName("stuName");

        User asd = new User()
                .setId(0)
                .setName("黑客")
                .setStudent(stuName);

        System.out.println(asd.toString());

        return service0Client.json(asd);
    }
}
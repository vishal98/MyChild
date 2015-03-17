import ghumover2.*

class BootStrap {



    def init = { servletContext ->

        Role roleTeacher;
        Role roleParent;
        Teacher teacher;
        Parent parent;


        roleTeacher = new Role(authority: 'ROLE_TEACHER')
        roleTeacher.save()

        roleParent = new Role(authority: 'ROLE_PARENT')
        roleParent.save()




        teacher = new Teacher(username: 'test_teacher', password: "123" , teacherId:100 , teacherName:"John" , teacherPhoto:"100.jpg")
        teacher.save()
        new UserRole(user:teacher , role:roleTeacher).save()








        parent = new Parent(username: 'test_parent', password: "123" ,parentName:"Ravi")
        parent.save()
        new UserRole(user:parent , role:roleParent).save()






        /*
            ADD 4 SAMPLE GRADES 5A,5B,10A AND 10B
         */
        new Grade(grade:5 , section:"A").save(flush:true)
        new Grade(grade:5 , section:"B").save(flush:true)
        new Grade(grade:10 , section:"A").save(flush:true)
        new Grade(grade:10 , section:"B").save(flush:true)

        /*
               Add 3 teacher entries
        */
        new Teacher(username: 'mathew', password: "123" ,teacherId:101 , teacherName:"Mathew" , teacherPhoto:"100.jpg").save(flush:true)
        new Teacher(username: 'sibi', password: "123" ,teacherId:102 , teacherName:"Sibi" , teacherPhoto:"101.jpg").save(flush:true)
        new Teacher(username: 'satheesh', password: "123" ,teacherId:103 , teacherName:"Satheesh" , teacherPhoto:"102.jpg").save(flush:true)



        /*
              Add 2 student entries and a parent entry ,  assing 2 students to that parent
        */
        def cl1 = Grade.get(1)
        def cl2 = Grade.get(3)

        new Student(studentId:100,studentName:"Rohith", grade:cl1 ,studentPhoto:"100.jpg").save(flush:true)
        new Student(studentId:101,studentName:"Rahul",grade:cl2,studentPhoto:"101.jpg").save(flush:true)
        def rohith = Student.get(1)
        def rahul = Student.get(2)

        new Parent(username: 'ravi', password: "123" ,parentName:"Ravi")
                .addToChildren(rohith)
                .addToChildren(rahul).save(flush:true)
        new Parent(username: 'hari', password: "123" ,parentName:"Hari").save(flush:true)


        /* Add  teachers to class 5A and also set one of the teacher as classteacher */
        roleParent = Role.findByAuthority('ROLE_PARENT')
        roleTeacher = Role.findByAuthority('ROLE_TEACHER')

        def mathew = Teacher.findByTeacherId(100)
        def sibi = Teacher.findByTeacherId(101)
        def sathees = Teacher.findByTeacherId(102)

        new UserRole(user:mathew , role:roleTeacher).save(flush:true)
        new UserRole(user:sibi , role:roleTeacher).save(flush:true)
        new UserRole(user:sathees , role:roleTeacher).save(flush:true)
        new UserRole(user:Parent.findByUsername('ravi') , role:roleParent).save(flush:true)
        new UserRole(user:Parent.findByUsername('hari') , role:roleParent ).save(flush:true)


        cl1.addToTeachers(mathew)

        cl1.addToTeachers(sibi)
        cl1.classTeacherId = mathew.id

        cl1.save(flush:true)



        /*
                Add some subjects and assing them to grades
        */
        new Subject(subjectId:100 ,subjectName:"English").save(flush:true)
        new Subject(subjectId:101 ,subjectName:"Hindi").save(flush:true)
        new Subject(subjectId:102 ,subjectName:"Physics").save(flush:true)
        new Subject(subjectId:103 ,subjectName:"Chemistry").save(flush:true)


        def english = Subject.get(1)
        def hindi = Subject.get(2)
        def physics = Subject.get(3)
        def chemistry = Subject.get(4)

        new GradeSubject(grade:5 ,subject:english).save(flush:true)
        new GradeSubject(grade:5 , subject:hindi).save(flush:true)
        new GradeSubject(grade:10 ,subject:physics).save(flush:true)
        new GradeSubject(grade:10 , subject:chemistry).save(flush:true);













    }

    def destroy = {
    }
}
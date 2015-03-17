package ghumover2
import grails.plugin.springsecurity.annotation.Secured


@Secured(['ROLE_TEACHER'])
class TeacherController  {

    def index(){

       render "This is index action of Teacher. Only Teacher can see    this"
    }


    def getSubject(){

        render "get Subject Action"
    }

    def getStudentList()
    {

        render "get Student List Action"
    }

}

package ghumover2
import grails.plugin.springsecurity.annotation.Secured


@Secured(['ROLE_PARENT'])
class ParentController {

   def index()
      {
           render "Only visible to parent"
      }
}

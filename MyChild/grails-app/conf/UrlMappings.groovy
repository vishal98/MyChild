class UrlMappings {

	static mappings = {



        "/subject/$grade"( controller : "teacher" ,  action : "getSubject")

        "/studentList/$grade/$studentSection"(controller : "teacher" , action : "getStudentList")


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

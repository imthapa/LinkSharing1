package linksharing

class AdminController {

    /*def index() {
        List<User> userList = User.findAll()
        render view: "dashboard", model: ["users": userList]
    }

    def changeActive(long id){
        log.info("$id")
        User user = User.get(id)
        user.active = !user.active
        user.save(flush:true)
        redirect(action:"index")
    }*/

    def index() {

        int offset, max
        if (params.offset)
            offset = params.int('offset')
        if (params.max)
            max = params.int('max')

//        List<User> userList = User.findAll()
        if (!offset)
            offset = 0
        if (!max)
            max = 5
        List<User> userList = User.createCriteria().list(offset: offset, max: 5) { }
        render view: "dashboard", model: ["users": userList]
    }

    def changeActive(long id) {
        log.info("++++++++++++++++++++++++++++++++++++++++${id}")
        User user = User.get(id)
        log.info("----------------------------------------${user}")
        user.active = !user.active
        user.save(flush: true, failOnError: true, validate: false)
        redirect(action:"index")
    }

    def showAllTopic() {
        int offset, max
        if (params.offset)
            offset = params.int('offset')
        if (params.max)
            max = params.int('max')
        if (!offset)
            offset = 0
        if (!max)
            max = 5

        List<Topic> topicList = Topic.createCriteria().list(offset:offset,max:max) {}
        render view: "topicList", model: ["topics": topicList]
    }

    def showAllPosts() {
        int offset, max
        if (params.offset)
            offset = params.int('offset')
        if (params.max)
            max = params.int('max')

        if (!offset)
            offset = 0
        if (!max)
            max = 10

        List<Resource> resourceList = Resource.createCriteria().list (offset:offset,max:max){}
        render view: "postList", model: ["posts": resourceList]
    }


}

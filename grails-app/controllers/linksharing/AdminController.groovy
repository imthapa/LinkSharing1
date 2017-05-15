package linksharing

class AdminController {

    def index() {
        List<User> userList = User.findAll()
        render view: "dashboard", model: ["users": userList]
    }

    def changeActive(long id){
        log.info("$id")
        User user = User.get(id)
        user.active = !user.active
        user.save(flush:true)
        redirect(action:"index")
    }

}

package linksharing

import com.ttnd.linksharing.co.UserCO
import org.springframework.web.multipart.MultipartFile

//todo Q1. Add logincontroller with index,loginHandler and logout action
class LoginController {

    def userService;

    //todo Q2. LoginController index action will check if there is session.user exists or not if exist forward to user controller index action else render failure
    def index() {
        // session['user'] = User.read(1).userName
        // log.info("${session.user}")
        //  if (session.user != null) {
//          log.info("aldhfkhdfkjhfkjhk")
        redirect(controller: 'user', action: 'index', params: [message: params.message])
        //   } else {
        //       List r = Resource.topPost()
        //     render "$r"
        //forward(action : 'loginHandler')
        //    }
        //render "hello welcome to grails"
    }

    //todo Q5. LoginController loginHandler action will take 2 argument username and password
    // url for viewing - http://localhost:8080/login/loginHandler?username=ishwar&password=qwertyuiop
    def loginHandler(String username, String password) {
        log.info("hello from loginhandler ${username} ${password}")
        //dummy data
        //   render ("params : username = ${username} , password : ${password}")
        //todo Q6. If Loginhandler action finds user with given username and password then it will check user active or not if active set session.user to user and redirect request to login index action
        User user = User.findByUserNameAndPassword(username, password)
        log.info("${user}")
        if (user) {
            if (user.active) {
                log.info("i am active hahahahaha")
                session.user = user//.userName
                log.info("${session.user.userName} is loggedIn")
                flash.message = message(code: "successful.login", args: [user.userName]);
//                render(view: '/user/dashboard', )
                redirect(action: 'index', params: [message: flash.message])
            } else {
                flash.message = message(code: "account.not.active");
//                flash.error = "Your account is not active"
                //todo Q7. If user is not active then set flash.error 'Your account is not active'
//                render "${flash.message}"
                render(view: "/user/regIndex")
            }

        } else {
            flash.message = message(code: "user.not.found")
            render(view: "/user/regIndex")//, model: ['postsList': Resource.topPost()]);
            //todo Q8. If user is not found then flash.error is set to 'User not found' and flash.error is rendered - Urlmapping is updated for / action to controller login action index
//            render "${flash.error}"
        }
    }

    //todo Q4 .Login Controller logout action will do session.invalidate and forward the request to login controller index action
    def logout() {
        session.invalidate()
        forward(action: 'index')
    }

    //todo Q21. Update bootstrap for user creation Create register action in login controller to register user
    //localhost:8080/login/register?firstName=akhil&lastName=rawat&email=akhil@ttn.com&userName=akhil&password=123456&active&admin=true
    def register(UserCO userCO) {
//        MultipartFile file = userCO.photo
        String photoPath = userService.uploadPhoto(userCO.userName,userCO.photo, request.getSession().getServletContext().getRealPath("/"))
        log.info("${userCO}")
        User user = new User(firstName: userCO.firstName, lastName: userCO.lastName, photoPath: photoPath, email: userCO.email,
                userName: userCO.userName, password: userCO.password, confirmPassword: userCO.confirmPassword)
        user.save(failOnError: true, flush: true)

        //todo Q22. Errors with proper message properties should be rendered if user is not set and flash message should be set.
        if (user.hasErrors()) {
            flash.error = "sorry you can't be registered."
            //log.info(user.errors.allErrors)
            //render user.errors.allErrors
            render "${flash.error}"
        } else {
//               session.user = user
            redirect action: 'loginHandler', params: [username: "${userCO.userName}", password: "${userCO.password}"]
        }
    }

    def forgotPassword(String email) {
        log.info("i am insisde forgot password+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        log.info("$email UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU-------------------------------------------------------")
        User user = User.findByEmail(email)
        println("$user    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        if (user) {
            String newPassword = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);

            sendMail {

                from "ishwarmanithapa@mgmail.com"
                to email
                cc "ishwarmani.thapa@tothenew.com"
                subject "Your New Password for Link Sharing"
                text """Hi, You password has been update. your new password is ${newPassword}.
                    please change your password after login."""

            }
            user.password = newPassword
            user.save(flush: true, failOnError: true)
        } else {
            flash.message = "sorry invalid Email-Id."
        }
        redirect(controller: "login", action: "loginHandler")
    }
}

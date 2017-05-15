package linksharing

import com.ttnd.linksharing.co.FileCO
import com.ttnd.linksharing.co.SearchCO
import com.ttnd.linksharing.co.UpdatePasswordCO
import com.ttnd.linksharing.co.UserCO
import com.ttnd.linksharing.vo.InboxVO
import com.ttnd.linksharing.vo.PostsVO
import com.ttnd.linksharing.vo.UserDetailsVO
import com.ttnd.linksharing.vo.TopicVO
import org.springframework.web.multipart.MultipartFile

import java.awt.image.BufferedImage
import javax.imageio.ImageIO;

//todo Q3. Add User controller with index action that will render text 'user dahsboard'
class UserController {

    def msg = flash.message ?: ""
    def subscriptionService
    def userService

    def index() {
        User user = session.user
        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getFullName()
        userDetailsVO.userName = user.userName
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
//      profilePageVO.photo = session.user.photo
        userDetailsVO.userId = user.id
        List<TopicVO> subscriptionList = User.getSubscribedTopic(user)
        List<PostsVO> messages = user.getUnReadResources()
        log.info("------------------------------------------ $messages")
        render view: 'dashboard', model: [users       : userDetailsVO, subscriptionList: subscriptionList,
                                          resourceList: messages, message: params.message]
    }
/*
    def index(SearchCO searchCO){
        render "${session.user}"
        render(view:"dashboard");
        log.info("${searchCO.q}")
        List list = User.getUnReadResources(searchCO)
        println(list)
        render(list)
    }
*/

/*
    def index(SearchCO searchCO) {
        log.info("wkwkwwkjwkjwk $searchCO.q")
        List list = User.getUnReadResources(searchCO)
        render "$list"
    }
*/

    def image(Long userId) {
        User user = User.load(userId)
//        byte[] photo
        String photoPath
        if (!user?.photoPath) {
            photoPath = assetResourceLocator.findAssetForURI('user.png').byteArray
        } else {
            photoPath = user.photoPath
            log.info("-----------------$photoPath----------------------")
            File imageFile = new File(photoPath)
            BufferedImage originalImage = ImageIO.read(imageFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            response.setHeader('Content-length', imageInByte.length.toString())
            response.contentType = 'image/jpg' // or the appropriate image content type
            response.outputStream << imageInByte
            response.outputStream.flush()
        }
//        OutputStream out = response.getOutputStream()
//        out.write(photo)
//        out.flush()
//        out.close()
    }

    def unread(SearchCO searchCO) {
        log.info("${searchCO.q}")
        List list = session.user.getUnReadResources()
        println(list)
        render(list)
    }

    def profile(long id) {

        User user = User.get(id)

        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getFullName()
        userDetailsVO.userName = user.userName
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
        userDetailsVO.userId = user.id

        List<TopicVO> subscriptionList = User.getSubscribedTopic(user)
        List<TopicVO> topicsCreated = User.allCreatedTopics(user)
        List<PostsVO> allPosts = User.allCreatedPost(user)
//        users: userDetailsVO,
        render view: "profile", model: ['subscriptionList': subscriptionList,
                                        topicsCreated     : topicsCreated, allPosts: allPosts]
    }

    def edit() {
        List<TopicVO> topicsCreated = User.allCreatedTopics(session.user)
        render view: "edit", model: [topicsCreated: topicsCreated]
    }
/*
    def updatePassword(UpdatePasswordCO co) {
        log.info("$co")
        User user = User.get(session.user.id)
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa $user")
//        if (co.password.equals(co.confirmPassword)){
//
//        }

        if (user.password.equals(co.oldPassword) ) {
                user.password = co.password
                user.confirmPassword = co.confirmPassword
                co.validate()
                if(co.hasError){
                    flash.error = "sorry "
                }
                else if (user.save(failOnError:true,flush: true)) {
                    flash.message = "Password changed successfully"
                    session.user = user
                } else {
                    flash.error = "Error in updating password"
                }
//            }

        } else {
            flash.error = "Old Password do not match!"
        }
        redirect(controller: "user", action: "editProfile")
    }*/


    def updatePassword(UpdatePasswordCO co) {
        log.info("$co")
        User user = User.get(session.user.id)
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa $user")
        if (co.password.equals(co.confirmPassword)) {
            if (user.password.equals(co.oldPassword)) {
                user.password = co.password
                user.confirmPassword = co.confirmPassword
                if (user.save(failOnError: true, flush: true)) {
                    flash.message = "Password changed successfully"
                    session.user = user
                } else {
                    flash.error = "Error in updating password"
                }
//            }

            } else {
                flash.error = "Old Password do not match!"
            }
        } else {
            flash.error = " Passwords do not match!"
        }
        redirect(controller: "user", action: "editProfile")
    }

    def updateProfile(UserCO co) {

        User user = User.findByUserName(session.user.userName)
        if (user) {
            if (co.firstName)
                user.firstName = co.firstName
            if (co.lastName)
                user.lastName = co.lastName
            if (co.userName)
                user.userName = co.userName
            if (co.photo) {
                user.photoPath = userService.uploadPhoto(user.userName, co.photo, request.getSession().getServletContext().getRealPath("/"))
            }
            if (user.save(failOnError: true, flush: true)) {
                flash.message = message(code: "profile.successfully.changed")
                msg = flash.message
                session.user = user
            } else {
                flash.error = "Error updating profile"
            }
        } else {
            flash.error = "User Not Found"
        }
//        render view: "edit", model: [message: msg]
        redirect(controller: "user", action: "editProfile", params: [message: msg])
    }

    def editProfile(def msg) {
        render view: "edit", model: [message: msg]
    }
}

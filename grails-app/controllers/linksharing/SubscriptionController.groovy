package linksharing

import com.gs.collections.impl.lazy.parallel.set.SelectUnsortedSetBatch
import com.ttnd.linksharing.util.Seriousness

class SubscriptionController {

    def subscriptionService

    def index() {}

    //todo Domain2 Q14) Implement subscription save, update, delete

    //todo Domain2 1Q6) Create save action which takes id as parameter for topic id, user for subscription should be read from the session, if subscription save render success else errors
    def save(long id) {
        println "SubscriptionController.save"
        User user = session.user
        subscriptionService.subscriptionUpdate(user, id)
//        redirect(controller: 'user', action: 'index')

    }
    //todo Domain2 Q15) Create subscription delete action which takes id as parameter, if it exist then delete and send success else render not found
    def delete(Integer id) {
        Subscription subscription = Subscription.get(id)
        if (subscription.topic.createdBy.id != session.user.id) {
            if (subscription) {
                subscription.delete(flush: true)
                flash.message = "Subscription deleted"
            } else {
                flash.error = "Error: Subscription not found"
            }
        } else {
            flash.error = "Creator can not delete subscription"
        }
        redirect(controller: 'user', action: 'index')
    }

    //todo Domain2 Q17) Create update action which takes an id and serious as a parameter if subscription and seriousness found, then save else render not found, if saved then render success else errors
    def update(long id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        def seriousness1 = Seriousness.toSeriousness(seriousness)
        log.info("$seriousness1 $id")
        if (subscription != null && seriousness1 instanceof Seriousness) {
            subscription.seriousness = seriousness1
            subscription.save(flush: true, failOnError: true)
            if (subscription.hasErrors()) {
                log.info("${subscription.errors.allErrors}")
                render "${subscription.errors.allErrors}"
            } else {
                log.info("$subscription is updated")
                render "$subscription is updated"
            }
        } else {
            render "subscription can't be updated"
        }
    }

    def allSubscribedUsers(){
        println("hello from subscribed users")
        Topic topic = Topic.get(1)
        list = topic.getSubscribedUsers(topic)
        render list
    }

    def viewAllSubscription(){
        def list=User.getSubscribedTopic(session.user)
        render view:'viewAllSubs', model: ['list':list]
    }

}

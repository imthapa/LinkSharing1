package linksharing

import com.ttnd.linksharing.vo.UserDetailsVO

class LinkSharingTagLib {
    static defaultEncodeAs = [taglib: 'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "ls"

    def trendingTopic = { attr, body ->
        out << render(template: "/topic/show", model: [topics: Topic.getTrendingTopics()])
    }

    def recentPosts = { attr, body ->
        out << render(template: "/topic/posts", model: [resourceList: Resource.recentPost()])
    }

    def topPosts = { attr, body ->
        out << render(template: "/topic/posts", model: [resourceList: Resource.topPost()])
    }

    def loggedInUser = { attr, body ->
        User user = session.user

        UserDetailsVO userDetailsVO = new UserDetailsVO()
        userDetailsVO.userFullName = user.getFullName()
        userDetailsVO.userName = user.userName
        userDetailsVO.subscriptionCount = Subscription.countByUser(user)
        userDetailsVO.topicCount = Topic.countByCreatedBy(user)
        userDetailsVO.userId = user.id

        out << render(template: "/user/show", model: [users: userDetailsVO])
    }

    def markAsRead = { attrs, body ->
        User user = session.user
        def resource = attrs.resource
        boolean isRead
        if (user && resource) {
//            if (resource) {
            resource = Resource.get(resource.resourceID)
            ReadingItem readingItem = ReadingItem.findByUserAndResource(user, resource)
            if (readingItem) {
                if (readingItem.isRead) {
                    body = "Mark As Unread"
                } else {
                    body = "Mark As Read"
                }
                out << link(controller: 'readingItem', action: 'changeIsRead', name: 'readingItem',
                        params: [id: resource.id, isRead: !readingItem.isRead], body)
            }
        }
    }

    def isLoggedIn = { attrs, body ->
        if (session.user) {
            out << body()
        }

    }

    def topicSubscriptionCount = { attrs ->
        long topicId = attrs.topicId
        def user = attrs.user

        int count = 0
        if (topicId) {
            count = Subscription.createCriteria().count {
                'topic' {
                    eq('id', topicId)
                }
            }
        } else if (user) {
            User user1 = User.get(user.userId)
            count = Subscription.createCriteria().count {
                eq('user', user1)
            }
        }
        out << count
    }

    def resourceCount = { attrs ->
        long topicId = attrs.topicId
        int count = 0
        if (topicId) {
            count = Resource.createCriteria().count {
                'topic' {
                    eq('id', topicId)
                }
            }
        }
        out << count
    }

    def topicCount = { attrs ->
        def user = attrs.user
        int count = 0
        if (user) {
            User user1 = User.get(user.userId)
            count = Topic.createCriteria().count {
                eq('createdBy', user1)
            }
        }
        out << count
    }


    def showSeriousness = { attrs, body ->
        User user = session.user
        Long topicId = attrs.topicId
        if (user && topicId) {
            Subscription subscription = Subscription.findByUserAndTopic(user, Topic.get(topicId))
//user.getSubscription(topicId)
//           log.info("$subscription--------------------------------------------------------")
            if (subscription) {
                out << render(template: '/topic/seriousness', model: [subscription: subscription])
            }
        }
    }

    def showVisibility = { attrs, body ->
        User user = session.user
        Long topicId = attrs.topicId
        if (user && topicId) {
            Topic topic = Topic.get(topicId)
            if (topic) {
                out << render(template: '/topic/visibility', model: [topic: topic])
            }
        }
    }

    def activateToggle ={ attr->
        long id = attr.id
        User user = User.get(id)
        if(user.active)
            out << "Deactivate"
        else
            out << "Activate"
    }
    /*def topiCreated = { attrs ->
        out << /<g:select name='id' from="${Topic.findAllByCreatedBy(session.user)}" optionKey='id' optionValue='name' class='form-control pull-right'
        defaultLabel='default topic' id='id'/>/
    }*/
    def subscriptionToggle = { attr->
        long topic_id = attr.id
        User user = session.user
        Topic topic = Topic.get(topic_id)
        Subscription subscription = Subscription.findByUserAndTopic(user,topic)
        if(subscription)
            out << "unsubscribe"
        else
            out << "subscribe"

    }

}

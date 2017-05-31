package com.ttnd.linksharing.vo

import linksharing.DocumentResource
import linksharing.LinkResource
import linksharing.Resource
import linksharing.User

/**
 * Created by ishwar on 10/5/17.
 */
class PostsVO {

    String topicName;
    long topicId
    String resourceDescription
    long resourceID
    User createdBy

    boolean isLinkResource(){
        if(Resource.get(resourceID) instanceof LinkResource)
            return true
        else
            return false

    }
    @Override
    public String toString() {
        return "PostsVO{" +
                "topicName='" + topicName + '\'' +
                ", topicId=" + topicId +
                ", resourceDescription='" + resourceDescription + '\'' +
                ", resourceID=" + resourceID +
                ", createdBy=" + createdBy +
                '}';
    }
}

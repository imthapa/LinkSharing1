package com.ttnd.linksharing.vo

import linksharing.DocumentResource
import linksharing.LinkResource
import linksharing.Resource

/**
 * Created by ishwar on 13/5/17.
 */
class DetailedPostVO {

    long createdById
    String userName
    String fullName
    String topicName
    long topicId
    String description
    int ratings
    Date updated
    long resourceID

    boolean isLinkResource(){
        Resource resource= Resource.get(resourceID)
        if(resource instanceof LinkResource)
            return true
        else if(resource instanceof DocumentResource)
            return false
    }

    @Override
    public String toString() {
        return "DetailedPostVO{" +
                "userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", updated=" + updated +
                ", resourceID=" + resourceID +
                '}';
    }
}

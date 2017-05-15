package linksharing

class ImageTagLib {
    static defaultEncodeAs = [taglib:'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "pic"
    def imageFile ={ attrs ->
        def userInstance = User.get(attrs.id)
        String photoPath = userInstance.photoPath
        if (!userInstance.photoPath ){
            log.info "eoeoeoeoeoeoeo"
            flash.error = "error!!!!!!!!!"
            return
        }
        OutputStream outputStream = response.getOutputStream()
        outputStream << image
        outputStream.flush()
        outputStream.close()
    }

    def userImage = { attrs, body ->
        User user = User.get(attrs.id)
        out << "<img alt='${user.userName}' style='max-width:100%;max-height:100%' class='media-object recent-media-object-custom' " +
                "src='${createLink(controller: 'user', action: 'image', params: [userId: attrs.id])}'>"
    }
}


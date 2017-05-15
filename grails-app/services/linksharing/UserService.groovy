package linksharing

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UserService {

    def uploadPhoto(String userName,MultipartFile file,String rootDir) {
        if (file.empty) {
            flash.message = 'file cannot be empty'
            render("error")
            return
        }
        //rootDir = request.getSession().getServletContext().getRealPath("/")
        File fileDest = new File(rootDir, "/uploads/${userName}_${file.originalFilename}")
        file.transferTo(fileDest)

        return "${rootDir}/uploads/${userName}_${file.originalFilename}"

    }
}

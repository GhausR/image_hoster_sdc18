package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    // This method is called when the user creates a comment
    // It add the user and image fields and then sends it off the service layer for persistence.
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment (@PathVariable("imageId") int imageId, @PathVariable("imageTitle") String imageTitle,
                                @RequestParam("comment") String textStr, HttpSession session) {

        User user = (User) session.getAttribute ("loggeduser");
        Comment comment = new Comment ();
        comment.setImage (imageService.getImage (imageId));
        comment.setText (textStr);
        comment.setUser (user);
        comment.setCreatedDate (LocalDate.now ());
        commentService.insertComment (comment);
        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
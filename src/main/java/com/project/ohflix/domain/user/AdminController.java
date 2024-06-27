package com.project.ohflix.domain.user;


import com.project.ohflix.domain._enums.Genre;
import com.project.ohflix.domain._enums.Rate;
import com.project.ohflix.domain.content.ContentRequest;
import com.project.ohflix.domain.content.ContentService;
import com.project.ohflix.domain.refund.RefundResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final HttpSession httpSession;
    private final UserService userService;
    private final ContentService contentService;


    @GetMapping("/admin/sales-page")
    public String getSales(HttpServletRequest request) {
        List<UserResponse.SalesPageDTO> respDTO = userService.salesPage();
        request.setAttribute("SalesPageDTO", respDTO);
        return "admin/sales-page";
    }

    @GetMapping("/admin/members-manage")
    public String getMembers(HttpServletRequest request) {
        List<UserResponse.MembersDTO> members = userService.MembersDTOList();
        long subscriberCount = members.stream().filter(UserResponse.MembersDTO::getIsSubscribe).count();
        request.setAttribute("members", members);
        request.setAttribute("subscriberCount", subscriberCount);
        return "admin/members-manage";
    }

    // 관리자 환불요청 관리 페이지 데이터
    @GetMapping("/admin/refund-manage")
    public String getRefund(HttpServletRequest request) {
        RefundResponse.ListDTO respDTO = userService.getRefundBoard();
        request.setAttribute("ListDTO", respDTO);
        return "admin/refund-manage";
    }

    // 영화 업로드
    @GetMapping("/admin/admin-upload")
    public String adminUpload(HttpServletRequest request) {
        request.setAttribute("genre", Genre.values());
        request.setAttribute("rate", Rate.values());
        return "admin/admin-upload";
    }

    @PostMapping("/admin/admin-upload")
    public String uploadInfo(ContentRequest.AdminUploadDTO requestDTO) {
        System.out.println("requestDTO = " + requestDTO);

        // 비디오 파일명을 가져와서 디렉토리 경로를 생성
        Path videoLocation = Paths.get("src/main/resources/static/images/movie/" + requestDTO
                .getFileName().toString().replace(".mp4", ""));

        // 디렉토리 경로에 폴더 생성
        try {
            Files.createDirectories(videoLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 각 파일에 대해 고유한 파일 경로를 생성하여 저장
        try {
            Path thumbnailPath = videoLocation.resolve(requestDTO.getThumbnail().getOriginalFilename());
            requestDTO.getThumbnail().transferTo(thumbnailPath);

            Path mainPhtoPath = videoLocation.resolve(requestDTO.getMainPhoto().getOriginalFilename());
            requestDTO.getMainPhoto().transferTo(mainPhtoPath);

            Path subPhotoPath = videoLocation.resolve(requestDTO.getPosterPhoto().getOriginalFilename());
            requestDTO.getPosterPhoto().transferTo(subPhotoPath);

            Path textPhotoPath = videoLocation.resolve(requestDTO.getTextPhoto().getOriginalFilename());
            requestDTO.getTextPhoto().transferTo(textPhotoPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        contentService.saveContent(requestDTO);

        return "redirect:/";
    }
}

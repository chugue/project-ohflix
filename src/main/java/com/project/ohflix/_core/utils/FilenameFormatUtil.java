package com.project.ohflix._core.utils;

public class FilenameFormatUtil {

    public static void main(String[] args) {
        String filePath = "";
        String parsedFileName = parseThumbnailFileName(filePath);
        System.out.println(parsedFileName); // ready_player_one_thumbnail.webp
    }

    public static String parseThumbnailFileName(String filePath) {
        // 파일 경로를 슬래시('/')로 분리하여 마지막 디렉터리 이름을 추출
        String[] parts = filePath.split("/");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid file path");
        }

        // 마지막 디렉터리 이름과 파일 이름을 결합
        String directoryName = parts[parts.length - 2];
        String fileName = parts[parts.length - 1];

        // 새로운 파일 이름 생성
        return directoryName + "_" + fileName;
    }

}

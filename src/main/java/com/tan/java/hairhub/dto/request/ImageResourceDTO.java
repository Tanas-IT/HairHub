package com.tan.java.hairhub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResourceDTO {
    private int imageId;
    private String imageURL;
    private String description;
}

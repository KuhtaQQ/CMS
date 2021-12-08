package com.example.mycms.form;

import com.example.mycms.entity.PageTextType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageTextForm {

    @NotNull
    private String identity;

    @NotNull
    private String content;

    @NotNull
    private Long pageId;

    @NotNull
    private PageTextType type;
}

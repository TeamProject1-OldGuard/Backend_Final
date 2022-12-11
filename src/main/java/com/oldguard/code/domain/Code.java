package com.oldguard.code.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Code {
    private Long id;
    private String code;

    @Builder
    public Code(Long id,String code) {
        this.id=id;
        this.code = code;
    }
}

package com.tarantini.lcbo.alkyhols;

import com.tarantini.lcbo.domain.gateway.Alkyhol;
import com.tarantini.lcbo.domain.gateway.LinkResponse;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
class AlkyholResponse extends LinkResponse {
    private Alkyhol alkyhol;
}

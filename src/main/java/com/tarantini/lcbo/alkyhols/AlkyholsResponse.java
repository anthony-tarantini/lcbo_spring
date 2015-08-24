package com.tarantini.lcbo.alkyhols;

import com.tarantini.lcbo.domain.gateway.Alkyhol;
import com.tarantini.lcbo.domain.gateway.LinkResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class AlkyholsResponse extends LinkResponse {
    private List<Alkyhol> alkyhols;
}

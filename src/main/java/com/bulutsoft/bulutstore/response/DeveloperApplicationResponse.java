package com.bulutsoft.bulutstore.response;

import com.bulutsoft.bulutstore.entity.DeveloperApplicationStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperApplicationResponse {
    private DeveloperApplicationStatus status;
    private LocalDateTime applicationDate;
}


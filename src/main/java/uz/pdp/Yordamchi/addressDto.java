package uz.pdp.Yordamchi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class addressDto {
    private String street;
    private Integer home_number;
    private Integer district_id;
}

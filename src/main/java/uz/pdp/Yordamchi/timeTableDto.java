package uz.pdp.Yordamchi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class timeTableDto {
    private Integer subject_id;
    private Integer teacher_id;
    private Integer group_id;
    private Integer day;
}

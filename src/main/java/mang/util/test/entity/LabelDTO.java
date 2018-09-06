package mang.util.test.entity;



import java.util.ArrayList;
import java.util.List;

public class LabelDTO {
    private String labelName;
    private String labelId;
    private String labelType;
    private List<LabelValueDTO> labelValue=new ArrayList<LabelValueDTO>();


    public void addLabelValue(LabelValueDTO labelValueDTO){
        this.labelValue.add(labelValueDTO);
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public List<LabelValueDTO> getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(List<LabelValueDTO> labelValue) {
        this.labelValue = labelValue;
    }
}

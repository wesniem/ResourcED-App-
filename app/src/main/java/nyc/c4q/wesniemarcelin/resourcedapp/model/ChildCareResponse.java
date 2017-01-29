package nyc.c4q.wesniemarcelin.resourcedapp.model;

import java.util.List;


public class ChildCareResponse {
    private boolean success;

    private List<ChildCareData> childCareData = null;

    public List<ChildCareData> getChildCareData() {
        return childCareData;
    }

    public boolean isSuccess() {
        return success;
    }
}

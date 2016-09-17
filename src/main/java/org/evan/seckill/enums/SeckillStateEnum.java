package org.evan.seckill.enums;

/**
 * Created by Evan on 9/18/2016.
 */
public enum SeckillStateEnum {
    SUCCESS(1,"��ɱ�ɹ�"),
    END(0,"��ɱ����"),
    REPEAT_KILL(-1,"�ظ���ɱ"),
    INNER_ERROR(-2,"�����쳣"),
    DATA_REWRITE(-3,"���ݴ۸�");

    private int state;
    private String stateInfo;


    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }


    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index){
        for(SeckillStateEnum state:values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
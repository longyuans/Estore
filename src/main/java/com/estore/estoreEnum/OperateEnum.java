package com.estore.estoreEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperateEnum {
    createUser,
    updateUser,
    createOrderLine,
    deleteOrderLine,
    createOrder,
    createReceiver,
    ;

    private String operate;

    /**
     * 判断operationType是否合法
     *
     * @param operationType
     * @return
     */
    public static boolean isIn(String operationType) {
        if (StringUtils.isBlank(operationType)) {
            return false;
        }
        return Arrays.asList(OperateEnum.values()).parallelStream().anyMatch(value ->
                StringUtils.equalsIgnoreCase(value.toString(), operationType));
    }

    public static void main(String[] args) {
        String s = "createUser";
        System.out.println(isIn(s));

    }
}

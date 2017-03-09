/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.resource.api;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum RpcOperateType implements org.apache.thrift.TEnum {
  Save(0),
  Delete(1),
  Update(2),
  Find(3),
  FindList(4),
  None(5);

  private final int value;

  private RpcOperateType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static RpcOperateType findByValue(int value) { 
    switch (value) {
      case 0:
        return Save;
      case 1:
        return Delete;
      case 2:
        return Update;
      case 3:
        return Find;
      case 4:
        return FindList;
      case 5:
        return None;
      default:
        return null;
    }
  }
}
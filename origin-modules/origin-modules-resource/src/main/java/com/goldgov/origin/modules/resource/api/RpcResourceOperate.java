/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.resource.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-09")
public class RpcResourceOperate implements org.apache.thrift.TBase<RpcResourceOperate, RpcResourceOperate._Fields>, java.io.Serializable, Cloneable, Comparable<RpcResourceOperate> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcResourceOperate");

  private static final org.apache.thrift.protocol.TField OPERATE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("operateID", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField OPERATE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("operateName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField OPERATE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("operateCode", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField OPERATE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("operateType", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField RESOURCE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceID", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcResourceOperateStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcResourceOperateTupleSchemeFactory();

  private int operateID; // required
  private java.lang.String operateName; // required
  private java.lang.String operateCode; // required
  private RpcOperateType operateType; // required
  private int resourceID; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    OPERATE_ID((short)1, "operateID"),
    OPERATE_NAME((short)2, "operateName"),
    OPERATE_CODE((short)3, "operateCode"),
    /**
     * 
     * @see RpcOperateType
     */
    OPERATE_TYPE((short)4, "operateType"),
    RESOURCE_ID((short)5, "resourceID");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // OPERATE_ID
          return OPERATE_ID;
        case 2: // OPERATE_NAME
          return OPERATE_NAME;
        case 3: // OPERATE_CODE
          return OPERATE_CODE;
        case 4: // OPERATE_TYPE
          return OPERATE_TYPE;
        case 5: // RESOURCE_ID
          return RESOURCE_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __OPERATEID_ISSET_ID = 0;
  private static final int __RESOURCEID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.OPERATE_ID, new org.apache.thrift.meta_data.FieldMetaData("operateID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.OPERATE_NAME, new org.apache.thrift.meta_data.FieldMetaData("operateName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OPERATE_CODE, new org.apache.thrift.meta_data.FieldMetaData("operateCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OPERATE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("operateType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, RpcOperateType.class)));
    tmpMap.put(_Fields.RESOURCE_ID, new org.apache.thrift.meta_data.FieldMetaData("resourceID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcResourceOperate.class, metaDataMap);
  }

  public RpcResourceOperate() {
  }

  public RpcResourceOperate(
    int operateID,
    java.lang.String operateName,
    java.lang.String operateCode,
    RpcOperateType operateType,
    int resourceID)
  {
    this();
    this.operateID = operateID;
    setOperateIDIsSet(true);
    this.operateName = operateName;
    this.operateCode = operateCode;
    this.operateType = operateType;
    this.resourceID = resourceID;
    setResourceIDIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcResourceOperate(RpcResourceOperate other) {
    __isset_bitfield = other.__isset_bitfield;
    this.operateID = other.operateID;
    if (other.isSetOperateName()) {
      this.operateName = other.operateName;
    }
    if (other.isSetOperateCode()) {
      this.operateCode = other.operateCode;
    }
    if (other.isSetOperateType()) {
      this.operateType = other.operateType;
    }
    this.resourceID = other.resourceID;
  }

  public RpcResourceOperate deepCopy() {
    return new RpcResourceOperate(this);
  }

  @Override
  public void clear() {
    setOperateIDIsSet(false);
    this.operateID = 0;
    this.operateName = null;
    this.operateCode = null;
    this.operateType = null;
    setResourceIDIsSet(false);
    this.resourceID = 0;
  }

  public int getOperateID() {
    return this.operateID;
  }

  public void setOperateID(int operateID) {
    this.operateID = operateID;
    setOperateIDIsSet(true);
  }

  public void unsetOperateID() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __OPERATEID_ISSET_ID);
  }

  /** Returns true if field operateID is set (has been assigned a value) and false otherwise */
  public boolean isSetOperateID() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __OPERATEID_ISSET_ID);
  }

  public void setOperateIDIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __OPERATEID_ISSET_ID, value);
  }

  public java.lang.String getOperateName() {
    return this.operateName;
  }

  public void setOperateName(java.lang.String operateName) {
    this.operateName = operateName;
  }

  public void unsetOperateName() {
    this.operateName = null;
  }

  /** Returns true if field operateName is set (has been assigned a value) and false otherwise */
  public boolean isSetOperateName() {
    return this.operateName != null;
  }

  public void setOperateNameIsSet(boolean value) {
    if (!value) {
      this.operateName = null;
    }
  }

  public java.lang.String getOperateCode() {
    return this.operateCode;
  }

  public void setOperateCode(java.lang.String operateCode) {
    this.operateCode = operateCode;
  }

  public void unsetOperateCode() {
    this.operateCode = null;
  }

  /** Returns true if field operateCode is set (has been assigned a value) and false otherwise */
  public boolean isSetOperateCode() {
    return this.operateCode != null;
  }

  public void setOperateCodeIsSet(boolean value) {
    if (!value) {
      this.operateCode = null;
    }
  }

  /**
   * 
   * @see RpcOperateType
   */
  public RpcOperateType getOperateType() {
    return this.operateType;
  }

  /**
   * 
   * @see RpcOperateType
   */
  public void setOperateType(RpcOperateType operateType) {
    this.operateType = operateType;
  }

  public void unsetOperateType() {
    this.operateType = null;
  }

  /** Returns true if field operateType is set (has been assigned a value) and false otherwise */
  public boolean isSetOperateType() {
    return this.operateType != null;
  }

  public void setOperateTypeIsSet(boolean value) {
    if (!value) {
      this.operateType = null;
    }
  }

  public int getResourceID() {
    return this.resourceID;
  }

  public void setResourceID(int resourceID) {
    this.resourceID = resourceID;
    setResourceIDIsSet(true);
  }

  public void unsetResourceID() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RESOURCEID_ISSET_ID);
  }

  /** Returns true if field resourceID is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceID() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RESOURCEID_ISSET_ID);
  }

  public void setResourceIDIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RESOURCEID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case OPERATE_ID:
      if (value == null) {
        unsetOperateID();
      } else {
        setOperateID((java.lang.Integer)value);
      }
      break;

    case OPERATE_NAME:
      if (value == null) {
        unsetOperateName();
      } else {
        setOperateName((java.lang.String)value);
      }
      break;

    case OPERATE_CODE:
      if (value == null) {
        unsetOperateCode();
      } else {
        setOperateCode((java.lang.String)value);
      }
      break;

    case OPERATE_TYPE:
      if (value == null) {
        unsetOperateType();
      } else {
        setOperateType((RpcOperateType)value);
      }
      break;

    case RESOURCE_ID:
      if (value == null) {
        unsetResourceID();
      } else {
        setResourceID((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case OPERATE_ID:
      return getOperateID();

    case OPERATE_NAME:
      return getOperateName();

    case OPERATE_CODE:
      return getOperateCode();

    case OPERATE_TYPE:
      return getOperateType();

    case RESOURCE_ID:
      return getResourceID();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case OPERATE_ID:
      return isSetOperateID();
    case OPERATE_NAME:
      return isSetOperateName();
    case OPERATE_CODE:
      return isSetOperateCode();
    case OPERATE_TYPE:
      return isSetOperateType();
    case RESOURCE_ID:
      return isSetResourceID();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcResourceOperate)
      return this.equals((RpcResourceOperate)that);
    return false;
  }

  public boolean equals(RpcResourceOperate that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_operateID = true;
    boolean that_present_operateID = true;
    if (this_present_operateID || that_present_operateID) {
      if (!(this_present_operateID && that_present_operateID))
        return false;
      if (this.operateID != that.operateID)
        return false;
    }

    boolean this_present_operateName = true && this.isSetOperateName();
    boolean that_present_operateName = true && that.isSetOperateName();
    if (this_present_operateName || that_present_operateName) {
      if (!(this_present_operateName && that_present_operateName))
        return false;
      if (!this.operateName.equals(that.operateName))
        return false;
    }

    boolean this_present_operateCode = true && this.isSetOperateCode();
    boolean that_present_operateCode = true && that.isSetOperateCode();
    if (this_present_operateCode || that_present_operateCode) {
      if (!(this_present_operateCode && that_present_operateCode))
        return false;
      if (!this.operateCode.equals(that.operateCode))
        return false;
    }

    boolean this_present_operateType = true && this.isSetOperateType();
    boolean that_present_operateType = true && that.isSetOperateType();
    if (this_present_operateType || that_present_operateType) {
      if (!(this_present_operateType && that_present_operateType))
        return false;
      if (!this.operateType.equals(that.operateType))
        return false;
    }

    boolean this_present_resourceID = true;
    boolean that_present_resourceID = true;
    if (this_present_resourceID || that_present_resourceID) {
      if (!(this_present_resourceID && that_present_resourceID))
        return false;
      if (this.resourceID != that.resourceID)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + operateID;

    hashCode = hashCode * 8191 + ((isSetOperateName()) ? 131071 : 524287);
    if (isSetOperateName())
      hashCode = hashCode * 8191 + operateName.hashCode();

    hashCode = hashCode * 8191 + ((isSetOperateCode()) ? 131071 : 524287);
    if (isSetOperateCode())
      hashCode = hashCode * 8191 + operateCode.hashCode();

    hashCode = hashCode * 8191 + ((isSetOperateType()) ? 131071 : 524287);
    if (isSetOperateType())
      hashCode = hashCode * 8191 + operateType.getValue();

    hashCode = hashCode * 8191 + resourceID;

    return hashCode;
  }

  @Override
  public int compareTo(RpcResourceOperate other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetOperateID()).compareTo(other.isSetOperateID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOperateID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.operateID, other.operateID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOperateName()).compareTo(other.isSetOperateName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOperateName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.operateName, other.operateName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOperateCode()).compareTo(other.isSetOperateCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOperateCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.operateCode, other.operateCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOperateType()).compareTo(other.isSetOperateType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOperateType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.operateType, other.operateType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResourceID()).compareTo(other.isSetResourceID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceID, other.resourceID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcResourceOperate(");
    boolean first = true;

    sb.append("operateID:");
    sb.append(this.operateID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("operateName:");
    if (this.operateName == null) {
      sb.append("null");
    } else {
      sb.append(this.operateName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("operateCode:");
    if (this.operateCode == null) {
      sb.append("null");
    } else {
      sb.append(this.operateCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("operateType:");
    if (this.operateType == null) {
      sb.append("null");
    } else {
      sb.append(this.operateType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceID:");
    sb.append(this.resourceID);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcResourceOperateStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcResourceOperateStandardScheme getScheme() {
      return new RpcResourceOperateStandardScheme();
    }
  }

  private static class RpcResourceOperateStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcResourceOperate> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcResourceOperate struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // OPERATE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.operateID = iprot.readI32();
              struct.setOperateIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // OPERATE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.operateName = iprot.readString();
              struct.setOperateNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // OPERATE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.operateCode = iprot.readString();
              struct.setOperateCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // OPERATE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.operateType = com.goldgov.origin.modules.resource.api.RpcOperateType.findByValue(iprot.readI32());
              struct.setOperateTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // RESOURCE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.resourceID = iprot.readI32();
              struct.setResourceIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcResourceOperate struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(OPERATE_ID_FIELD_DESC);
      oprot.writeI32(struct.operateID);
      oprot.writeFieldEnd();
      if (struct.operateName != null) {
        oprot.writeFieldBegin(OPERATE_NAME_FIELD_DESC);
        oprot.writeString(struct.operateName);
        oprot.writeFieldEnd();
      }
      if (struct.operateCode != null) {
        oprot.writeFieldBegin(OPERATE_CODE_FIELD_DESC);
        oprot.writeString(struct.operateCode);
        oprot.writeFieldEnd();
      }
      if (struct.operateType != null) {
        oprot.writeFieldBegin(OPERATE_TYPE_FIELD_DESC);
        oprot.writeI32(struct.operateType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(RESOURCE_ID_FIELD_DESC);
      oprot.writeI32(struct.resourceID);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcResourceOperateTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcResourceOperateTupleScheme getScheme() {
      return new RpcResourceOperateTupleScheme();
    }
  }

  private static class RpcResourceOperateTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcResourceOperate> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcResourceOperate struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetOperateID()) {
        optionals.set(0);
      }
      if (struct.isSetOperateName()) {
        optionals.set(1);
      }
      if (struct.isSetOperateCode()) {
        optionals.set(2);
      }
      if (struct.isSetOperateType()) {
        optionals.set(3);
      }
      if (struct.isSetResourceID()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetOperateID()) {
        oprot.writeI32(struct.operateID);
      }
      if (struct.isSetOperateName()) {
        oprot.writeString(struct.operateName);
      }
      if (struct.isSetOperateCode()) {
        oprot.writeString(struct.operateCode);
      }
      if (struct.isSetOperateType()) {
        oprot.writeI32(struct.operateType.getValue());
      }
      if (struct.isSetResourceID()) {
        oprot.writeI32(struct.resourceID);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcResourceOperate struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.operateID = iprot.readI32();
        struct.setOperateIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.operateName = iprot.readString();
        struct.setOperateNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.operateCode = iprot.readString();
        struct.setOperateCodeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.operateType = com.goldgov.origin.modules.resource.api.RpcOperateType.findByValue(iprot.readI32());
        struct.setOperateTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.resourceID = iprot.readI32();
        struct.setResourceIDIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


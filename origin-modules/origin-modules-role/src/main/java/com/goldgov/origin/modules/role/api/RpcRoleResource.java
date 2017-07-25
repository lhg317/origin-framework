/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.role.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-07-25")
public class RpcRoleResource implements org.apache.thrift.TBase<RpcRoleResource, RpcRoleResource._Fields>, java.io.Serializable, Cloneable, Comparable<RpcRoleResource> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcRoleResource");

  private static final org.apache.thrift.protocol.TField ROLE_RESOURCE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roleResourceID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ROLE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roleID", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField RESOURCE_OPERATE_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceOperate", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcRoleResourceStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcRoleResourceTupleSchemeFactory();

  private java.lang.String roleResourceID; // required
  private java.lang.String roleID; // required
  private java.lang.String resourceOperate; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROLE_RESOURCE_ID((short)1, "roleResourceID"),
    ROLE_ID((short)2, "roleID"),
    RESOURCE_OPERATE((short)3, "resourceOperate");

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
        case 1: // ROLE_RESOURCE_ID
          return ROLE_RESOURCE_ID;
        case 2: // ROLE_ID
          return ROLE_ID;
        case 3: // RESOURCE_OPERATE
          return RESOURCE_OPERATE;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROLE_RESOURCE_ID, new org.apache.thrift.meta_data.FieldMetaData("roleResourceID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLE_ID, new org.apache.thrift.meta_data.FieldMetaData("roleID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RESOURCE_OPERATE, new org.apache.thrift.meta_data.FieldMetaData("resourceOperate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcRoleResource.class, metaDataMap);
  }

  public RpcRoleResource() {
  }

  public RpcRoleResource(
    java.lang.String roleResourceID,
    java.lang.String roleID,
    java.lang.String resourceOperate)
  {
    this();
    this.roleResourceID = roleResourceID;
    this.roleID = roleID;
    this.resourceOperate = resourceOperate;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcRoleResource(RpcRoleResource other) {
    if (other.isSetRoleResourceID()) {
      this.roleResourceID = other.roleResourceID;
    }
    if (other.isSetRoleID()) {
      this.roleID = other.roleID;
    }
    if (other.isSetResourceOperate()) {
      this.resourceOperate = other.resourceOperate;
    }
  }

  public RpcRoleResource deepCopy() {
    return new RpcRoleResource(this);
  }

  @Override
  public void clear() {
    this.roleResourceID = null;
    this.roleID = null;
    this.resourceOperate = null;
  }

  public java.lang.String getRoleResourceID() {
    return this.roleResourceID;
  }

  public void setRoleResourceID(java.lang.String roleResourceID) {
    this.roleResourceID = roleResourceID;
  }

  public void unsetRoleResourceID() {
    this.roleResourceID = null;
  }

  /** Returns true if field roleResourceID is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleResourceID() {
    return this.roleResourceID != null;
  }

  public void setRoleResourceIDIsSet(boolean value) {
    if (!value) {
      this.roleResourceID = null;
    }
  }

  public java.lang.String getRoleID() {
    return this.roleID;
  }

  public void setRoleID(java.lang.String roleID) {
    this.roleID = roleID;
  }

  public void unsetRoleID() {
    this.roleID = null;
  }

  /** Returns true if field roleID is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleID() {
    return this.roleID != null;
  }

  public void setRoleIDIsSet(boolean value) {
    if (!value) {
      this.roleID = null;
    }
  }

  public java.lang.String getResourceOperate() {
    return this.resourceOperate;
  }

  public void setResourceOperate(java.lang.String resourceOperate) {
    this.resourceOperate = resourceOperate;
  }

  public void unsetResourceOperate() {
    this.resourceOperate = null;
  }

  /** Returns true if field resourceOperate is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceOperate() {
    return this.resourceOperate != null;
  }

  public void setResourceOperateIsSet(boolean value) {
    if (!value) {
      this.resourceOperate = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROLE_RESOURCE_ID:
      if (value == null) {
        unsetRoleResourceID();
      } else {
        setRoleResourceID((java.lang.String)value);
      }
      break;

    case ROLE_ID:
      if (value == null) {
        unsetRoleID();
      } else {
        setRoleID((java.lang.String)value);
      }
      break;

    case RESOURCE_OPERATE:
      if (value == null) {
        unsetResourceOperate();
      } else {
        setResourceOperate((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROLE_RESOURCE_ID:
      return getRoleResourceID();

    case ROLE_ID:
      return getRoleID();

    case RESOURCE_OPERATE:
      return getResourceOperate();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROLE_RESOURCE_ID:
      return isSetRoleResourceID();
    case ROLE_ID:
      return isSetRoleID();
    case RESOURCE_OPERATE:
      return isSetResourceOperate();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcRoleResource)
      return this.equals((RpcRoleResource)that);
    return false;
  }

  public boolean equals(RpcRoleResource that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_roleResourceID = true && this.isSetRoleResourceID();
    boolean that_present_roleResourceID = true && that.isSetRoleResourceID();
    if (this_present_roleResourceID || that_present_roleResourceID) {
      if (!(this_present_roleResourceID && that_present_roleResourceID))
        return false;
      if (!this.roleResourceID.equals(that.roleResourceID))
        return false;
    }

    boolean this_present_roleID = true && this.isSetRoleID();
    boolean that_present_roleID = true && that.isSetRoleID();
    if (this_present_roleID || that_present_roleID) {
      if (!(this_present_roleID && that_present_roleID))
        return false;
      if (!this.roleID.equals(that.roleID))
        return false;
    }

    boolean this_present_resourceOperate = true && this.isSetResourceOperate();
    boolean that_present_resourceOperate = true && that.isSetResourceOperate();
    if (this_present_resourceOperate || that_present_resourceOperate) {
      if (!(this_present_resourceOperate && that_present_resourceOperate))
        return false;
      if (!this.resourceOperate.equals(that.resourceOperate))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRoleResourceID()) ? 131071 : 524287);
    if (isSetRoleResourceID())
      hashCode = hashCode * 8191 + roleResourceID.hashCode();

    hashCode = hashCode * 8191 + ((isSetRoleID()) ? 131071 : 524287);
    if (isSetRoleID())
      hashCode = hashCode * 8191 + roleID.hashCode();

    hashCode = hashCode * 8191 + ((isSetResourceOperate()) ? 131071 : 524287);
    if (isSetResourceOperate())
      hashCode = hashCode * 8191 + resourceOperate.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcRoleResource other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRoleResourceID()).compareTo(other.isSetRoleResourceID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleResourceID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleResourceID, other.roleResourceID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoleID()).compareTo(other.isSetRoleID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleID, other.roleID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResourceOperate()).compareTo(other.isSetResourceOperate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceOperate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceOperate, other.resourceOperate);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcRoleResource(");
    boolean first = true;

    sb.append("roleResourceID:");
    if (this.roleResourceID == null) {
      sb.append("null");
    } else {
      sb.append(this.roleResourceID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roleID:");
    if (this.roleID == null) {
      sb.append("null");
    } else {
      sb.append(this.roleID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceOperate:");
    if (this.resourceOperate == null) {
      sb.append("null");
    } else {
      sb.append(this.resourceOperate);
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcRoleResourceStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleResourceStandardScheme getScheme() {
      return new RpcRoleResourceStandardScheme();
    }
  }

  private static class RpcRoleResourceStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcRoleResource> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcRoleResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROLE_RESOURCE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleResourceID = iprot.readString();
              struct.setRoleResourceIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROLE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleID = iprot.readString();
              struct.setRoleIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // RESOURCE_OPERATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.resourceOperate = iprot.readString();
              struct.setResourceOperateIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcRoleResource struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.roleResourceID != null) {
        oprot.writeFieldBegin(ROLE_RESOURCE_ID_FIELD_DESC);
        oprot.writeString(struct.roleResourceID);
        oprot.writeFieldEnd();
      }
      if (struct.roleID != null) {
        oprot.writeFieldBegin(ROLE_ID_FIELD_DESC);
        oprot.writeString(struct.roleID);
        oprot.writeFieldEnd();
      }
      if (struct.resourceOperate != null) {
        oprot.writeFieldBegin(RESOURCE_OPERATE_FIELD_DESC);
        oprot.writeString(struct.resourceOperate);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcRoleResourceTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleResourceTupleScheme getScheme() {
      return new RpcRoleResourceTupleScheme();
    }
  }

  private static class RpcRoleResourceTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcRoleResource> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcRoleResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRoleResourceID()) {
        optionals.set(0);
      }
      if (struct.isSetRoleID()) {
        optionals.set(1);
      }
      if (struct.isSetResourceOperate()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRoleResourceID()) {
        oprot.writeString(struct.roleResourceID);
      }
      if (struct.isSetRoleID()) {
        oprot.writeString(struct.roleID);
      }
      if (struct.isSetResourceOperate()) {
        oprot.writeString(struct.resourceOperate);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcRoleResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.roleResourceID = iprot.readString();
        struct.setRoleResourceIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.roleID = iprot.readString();
        struct.setRoleIDIsSet(true);
      }
      if (incoming.get(2)) {
        struct.resourceOperate = iprot.readString();
        struct.setResourceOperateIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


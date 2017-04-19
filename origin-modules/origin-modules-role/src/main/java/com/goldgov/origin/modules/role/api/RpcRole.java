/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.role.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-04-19")
public class RpcRole implements org.apache.thrift.TBase<RpcRole, RpcRole._Fields>, java.io.Serializable, Cloneable, Comparable<RpcRole> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcRole");

  private static final org.apache.thrift.protocol.TField ROLE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roleID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ROLE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("roleName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ROLE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("roleCode", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcRoleStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcRoleTupleSchemeFactory();

  private java.lang.String roleID; // required
  private java.lang.String roleName; // required
  private java.lang.String roleCode; // required
  private java.lang.String description; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROLE_ID((short)1, "roleID"),
    ROLE_NAME((short)2, "roleName"),
    ROLE_CODE((short)3, "roleCode"),
    DESCRIPTION((short)4, "description");

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
        case 1: // ROLE_ID
          return ROLE_ID;
        case 2: // ROLE_NAME
          return ROLE_NAME;
        case 3: // ROLE_CODE
          return ROLE_CODE;
        case 4: // DESCRIPTION
          return DESCRIPTION;
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
    tmpMap.put(_Fields.ROLE_ID, new org.apache.thrift.meta_data.FieldMetaData("roleID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLE_NAME, new org.apache.thrift.meta_data.FieldMetaData("roleName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLE_CODE, new org.apache.thrift.meta_data.FieldMetaData("roleCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcRole.class, metaDataMap);
  }

  public RpcRole() {
  }

  public RpcRole(
    java.lang.String roleID,
    java.lang.String roleName,
    java.lang.String roleCode,
    java.lang.String description)
  {
    this();
    this.roleID = roleID;
    this.roleName = roleName;
    this.roleCode = roleCode;
    this.description = description;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcRole(RpcRole other) {
    if (other.isSetRoleID()) {
      this.roleID = other.roleID;
    }
    if (other.isSetRoleName()) {
      this.roleName = other.roleName;
    }
    if (other.isSetRoleCode()) {
      this.roleCode = other.roleCode;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
  }

  public RpcRole deepCopy() {
    return new RpcRole(this);
  }

  @Override
  public void clear() {
    this.roleID = null;
    this.roleName = null;
    this.roleCode = null;
    this.description = null;
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

  public java.lang.String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(java.lang.String roleName) {
    this.roleName = roleName;
  }

  public void unsetRoleName() {
    this.roleName = null;
  }

  /** Returns true if field roleName is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleName() {
    return this.roleName != null;
  }

  public void setRoleNameIsSet(boolean value) {
    if (!value) {
      this.roleName = null;
    }
  }

  public java.lang.String getRoleCode() {
    return this.roleCode;
  }

  public void setRoleCode(java.lang.String roleCode) {
    this.roleCode = roleCode;
  }

  public void unsetRoleCode() {
    this.roleCode = null;
  }

  /** Returns true if field roleCode is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleCode() {
    return this.roleCode != null;
  }

  public void setRoleCodeIsSet(boolean value) {
    if (!value) {
      this.roleCode = null;
    }
  }

  public java.lang.String getDescription() {
    return this.description;
  }

  public void setDescription(java.lang.String description) {
    this.description = description;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROLE_ID:
      if (value == null) {
        unsetRoleID();
      } else {
        setRoleID((java.lang.String)value);
      }
      break;

    case ROLE_NAME:
      if (value == null) {
        unsetRoleName();
      } else {
        setRoleName((java.lang.String)value);
      }
      break;

    case ROLE_CODE:
      if (value == null) {
        unsetRoleCode();
      } else {
        setRoleCode((java.lang.String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROLE_ID:
      return getRoleID();

    case ROLE_NAME:
      return getRoleName();

    case ROLE_CODE:
      return getRoleCode();

    case DESCRIPTION:
      return getDescription();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROLE_ID:
      return isSetRoleID();
    case ROLE_NAME:
      return isSetRoleName();
    case ROLE_CODE:
      return isSetRoleCode();
    case DESCRIPTION:
      return isSetDescription();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcRole)
      return this.equals((RpcRole)that);
    return false;
  }

  public boolean equals(RpcRole that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_roleID = true && this.isSetRoleID();
    boolean that_present_roleID = true && that.isSetRoleID();
    if (this_present_roleID || that_present_roleID) {
      if (!(this_present_roleID && that_present_roleID))
        return false;
      if (!this.roleID.equals(that.roleID))
        return false;
    }

    boolean this_present_roleName = true && this.isSetRoleName();
    boolean that_present_roleName = true && that.isSetRoleName();
    if (this_present_roleName || that_present_roleName) {
      if (!(this_present_roleName && that_present_roleName))
        return false;
      if (!this.roleName.equals(that.roleName))
        return false;
    }

    boolean this_present_roleCode = true && this.isSetRoleCode();
    boolean that_present_roleCode = true && that.isSetRoleCode();
    if (this_present_roleCode || that_present_roleCode) {
      if (!(this_present_roleCode && that_present_roleCode))
        return false;
      if (!this.roleCode.equals(that.roleCode))
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRoleID()) ? 131071 : 524287);
    if (isSetRoleID())
      hashCode = hashCode * 8191 + roleID.hashCode();

    hashCode = hashCode * 8191 + ((isSetRoleName()) ? 131071 : 524287);
    if (isSetRoleName())
      hashCode = hashCode * 8191 + roleName.hashCode();

    hashCode = hashCode * 8191 + ((isSetRoleCode()) ? 131071 : 524287);
    if (isSetRoleCode())
      hashCode = hashCode * 8191 + roleCode.hashCode();

    hashCode = hashCode * 8191 + ((isSetDescription()) ? 131071 : 524287);
    if (isSetDescription())
      hashCode = hashCode * 8191 + description.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcRole other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = java.lang.Boolean.valueOf(isSetRoleName()).compareTo(other.isSetRoleName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleName, other.roleName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRoleCode()).compareTo(other.isSetRoleCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleCode, other.roleCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDescription()).compareTo(other.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, other.description);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcRole(");
    boolean first = true;

    sb.append("roleID:");
    if (this.roleID == null) {
      sb.append("null");
    } else {
      sb.append(this.roleID);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roleName:");
    if (this.roleName == null) {
      sb.append("null");
    } else {
      sb.append(this.roleName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roleCode:");
    if (this.roleCode == null) {
      sb.append("null");
    } else {
      sb.append(this.roleCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
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

  private static class RpcRoleStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleStandardScheme getScheme() {
      return new RpcRoleStandardScheme();
    }
  }

  private static class RpcRoleStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcRole> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcRole struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROLE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleID = iprot.readString();
              struct.setRoleIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROLE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleName = iprot.readString();
              struct.setRoleNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ROLE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleCode = iprot.readString();
              struct.setRoleCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcRole struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.roleID != null) {
        oprot.writeFieldBegin(ROLE_ID_FIELD_DESC);
        oprot.writeString(struct.roleID);
        oprot.writeFieldEnd();
      }
      if (struct.roleName != null) {
        oprot.writeFieldBegin(ROLE_NAME_FIELD_DESC);
        oprot.writeString(struct.roleName);
        oprot.writeFieldEnd();
      }
      if (struct.roleCode != null) {
        oprot.writeFieldBegin(ROLE_CODE_FIELD_DESC);
        oprot.writeString(struct.roleCode);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcRoleTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcRoleTupleScheme getScheme() {
      return new RpcRoleTupleScheme();
    }
  }

  private static class RpcRoleTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcRole> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcRole struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRoleID()) {
        optionals.set(0);
      }
      if (struct.isSetRoleName()) {
        optionals.set(1);
      }
      if (struct.isSetRoleCode()) {
        optionals.set(2);
      }
      if (struct.isSetDescription()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetRoleID()) {
        oprot.writeString(struct.roleID);
      }
      if (struct.isSetRoleName()) {
        oprot.writeString(struct.roleName);
      }
      if (struct.isSetRoleCode()) {
        oprot.writeString(struct.roleCode);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcRole struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.roleID = iprot.readString();
        struct.setRoleIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.roleName = iprot.readString();
        struct.setRoleNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.roleCode = iprot.readString();
        struct.setRoleCodeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


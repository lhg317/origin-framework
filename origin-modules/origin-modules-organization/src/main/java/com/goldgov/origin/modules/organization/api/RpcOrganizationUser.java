/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.organization.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-08-12")
public class RpcOrganizationUser implements org.apache.thrift.TBase<RpcOrganizationUser, RpcOrganizationUser._Fields>, java.io.Serializable, Cloneable, Comparable<RpcOrganizationUser> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcOrganizationUser");

  private static final org.apache.thrift.protocol.TField ORG_USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("orgUserID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ORG_USER_FIELD_DESC = new org.apache.thrift.protocol.TField("orgUser", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ORGANIZATION_FIELD_DESC = new org.apache.thrift.protocol.TField("organization", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcOrganizationUserStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcOrganizationUserTupleSchemeFactory();

  private java.lang.String orgUserID; // optional
  private java.lang.String orgUser; // optional
  private RpcOrganization organization; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ORG_USER_ID((short)1, "orgUserID"),
    ORG_USER((short)2, "orgUser"),
    ORGANIZATION((short)3, "organization");

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
        case 1: // ORG_USER_ID
          return ORG_USER_ID;
        case 2: // ORG_USER
          return ORG_USER;
        case 3: // ORGANIZATION
          return ORGANIZATION;
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
  private static final _Fields optionals[] = {_Fields.ORG_USER_ID,_Fields.ORG_USER,_Fields.ORGANIZATION};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ORG_USER_ID, new org.apache.thrift.meta_data.FieldMetaData("orgUserID", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORG_USER, new org.apache.thrift.meta_data.FieldMetaData("orgUser", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORGANIZATION, new org.apache.thrift.meta_data.FieldMetaData("organization", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RpcOrganization.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcOrganizationUser.class, metaDataMap);
  }

  public RpcOrganizationUser() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcOrganizationUser(RpcOrganizationUser other) {
    if (other.isSetOrgUserID()) {
      this.orgUserID = other.orgUserID;
    }
    if (other.isSetOrgUser()) {
      this.orgUser = other.orgUser;
    }
    if (other.isSetOrganization()) {
      this.organization = new RpcOrganization(other.organization);
    }
  }

  public RpcOrganizationUser deepCopy() {
    return new RpcOrganizationUser(this);
  }

  @Override
  public void clear() {
    this.orgUserID = null;
    this.orgUser = null;
    this.organization = null;
  }

  public java.lang.String getOrgUserID() {
    return this.orgUserID;
  }

  public void setOrgUserID(java.lang.String orgUserID) {
    this.orgUserID = orgUserID;
  }

  public void unsetOrgUserID() {
    this.orgUserID = null;
  }

  /** Returns true if field orgUserID is set (has been assigned a value) and false otherwise */
  public boolean isSetOrgUserID() {
    return this.orgUserID != null;
  }

  public void setOrgUserIDIsSet(boolean value) {
    if (!value) {
      this.orgUserID = null;
    }
  }

  public java.lang.String getOrgUser() {
    return this.orgUser;
  }

  public void setOrgUser(java.lang.String orgUser) {
    this.orgUser = orgUser;
  }

  public void unsetOrgUser() {
    this.orgUser = null;
  }

  /** Returns true if field orgUser is set (has been assigned a value) and false otherwise */
  public boolean isSetOrgUser() {
    return this.orgUser != null;
  }

  public void setOrgUserIsSet(boolean value) {
    if (!value) {
      this.orgUser = null;
    }
  }

  public RpcOrganization getOrganization() {
    return this.organization;
  }

  public void setOrganization(RpcOrganization organization) {
    this.organization = organization;
  }

  public void unsetOrganization() {
    this.organization = null;
  }

  /** Returns true if field organization is set (has been assigned a value) and false otherwise */
  public boolean isSetOrganization() {
    return this.organization != null;
  }

  public void setOrganizationIsSet(boolean value) {
    if (!value) {
      this.organization = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ORG_USER_ID:
      if (value == null) {
        unsetOrgUserID();
      } else {
        setOrgUserID((java.lang.String)value);
      }
      break;

    case ORG_USER:
      if (value == null) {
        unsetOrgUser();
      } else {
        setOrgUser((java.lang.String)value);
      }
      break;

    case ORGANIZATION:
      if (value == null) {
        unsetOrganization();
      } else {
        setOrganization((RpcOrganization)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ORG_USER_ID:
      return getOrgUserID();

    case ORG_USER:
      return getOrgUser();

    case ORGANIZATION:
      return getOrganization();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ORG_USER_ID:
      return isSetOrgUserID();
    case ORG_USER:
      return isSetOrgUser();
    case ORGANIZATION:
      return isSetOrganization();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcOrganizationUser)
      return this.equals((RpcOrganizationUser)that);
    return false;
  }

  public boolean equals(RpcOrganizationUser that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_orgUserID = true && this.isSetOrgUserID();
    boolean that_present_orgUserID = true && that.isSetOrgUserID();
    if (this_present_orgUserID || that_present_orgUserID) {
      if (!(this_present_orgUserID && that_present_orgUserID))
        return false;
      if (!this.orgUserID.equals(that.orgUserID))
        return false;
    }

    boolean this_present_orgUser = true && this.isSetOrgUser();
    boolean that_present_orgUser = true && that.isSetOrgUser();
    if (this_present_orgUser || that_present_orgUser) {
      if (!(this_present_orgUser && that_present_orgUser))
        return false;
      if (!this.orgUser.equals(that.orgUser))
        return false;
    }

    boolean this_present_organization = true && this.isSetOrganization();
    boolean that_present_organization = true && that.isSetOrganization();
    if (this_present_organization || that_present_organization) {
      if (!(this_present_organization && that_present_organization))
        return false;
      if (!this.organization.equals(that.organization))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetOrgUserID()) ? 131071 : 524287);
    if (isSetOrgUserID())
      hashCode = hashCode * 8191 + orgUserID.hashCode();

    hashCode = hashCode * 8191 + ((isSetOrgUser()) ? 131071 : 524287);
    if (isSetOrgUser())
      hashCode = hashCode * 8191 + orgUser.hashCode();

    hashCode = hashCode * 8191 + ((isSetOrganization()) ? 131071 : 524287);
    if (isSetOrganization())
      hashCode = hashCode * 8191 + organization.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcOrganizationUser other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetOrgUserID()).compareTo(other.isSetOrgUserID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrgUserID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orgUserID, other.orgUserID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOrgUser()).compareTo(other.isSetOrgUser());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrgUser()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orgUser, other.orgUser);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOrganization()).compareTo(other.isSetOrganization());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrganization()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.organization, other.organization);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcOrganizationUser(");
    boolean first = true;

    if (isSetOrgUserID()) {
      sb.append("orgUserID:");
      if (this.orgUserID == null) {
        sb.append("null");
      } else {
        sb.append(this.orgUserID);
      }
      first = false;
    }
    if (isSetOrgUser()) {
      if (!first) sb.append(", ");
      sb.append("orgUser:");
      if (this.orgUser == null) {
        sb.append("null");
      } else {
        sb.append(this.orgUser);
      }
      first = false;
    }
    if (isSetOrganization()) {
      if (!first) sb.append(", ");
      sb.append("organization:");
      if (this.organization == null) {
        sb.append("null");
      } else {
        sb.append(this.organization);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (organization != null) {
      organization.validate();
    }
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

  private static class RpcOrganizationUserStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcOrganizationUserStandardScheme getScheme() {
      return new RpcOrganizationUserStandardScheme();
    }
  }

  private static class RpcOrganizationUserStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcOrganizationUser> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcOrganizationUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ORG_USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.orgUserID = iprot.readString();
              struct.setOrgUserIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ORG_USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.orgUser = iprot.readString();
              struct.setOrgUserIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ORGANIZATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.organization = new RpcOrganization();
              struct.organization.read(iprot);
              struct.setOrganizationIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcOrganizationUser struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.orgUserID != null) {
        if (struct.isSetOrgUserID()) {
          oprot.writeFieldBegin(ORG_USER_ID_FIELD_DESC);
          oprot.writeString(struct.orgUserID);
          oprot.writeFieldEnd();
        }
      }
      if (struct.orgUser != null) {
        if (struct.isSetOrgUser()) {
          oprot.writeFieldBegin(ORG_USER_FIELD_DESC);
          oprot.writeString(struct.orgUser);
          oprot.writeFieldEnd();
        }
      }
      if (struct.organization != null) {
        if (struct.isSetOrganization()) {
          oprot.writeFieldBegin(ORGANIZATION_FIELD_DESC);
          struct.organization.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcOrganizationUserTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcOrganizationUserTupleScheme getScheme() {
      return new RpcOrganizationUserTupleScheme();
    }
  }

  private static class RpcOrganizationUserTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcOrganizationUser> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcOrganizationUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetOrgUserID()) {
        optionals.set(0);
      }
      if (struct.isSetOrgUser()) {
        optionals.set(1);
      }
      if (struct.isSetOrganization()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetOrgUserID()) {
        oprot.writeString(struct.orgUserID);
      }
      if (struct.isSetOrgUser()) {
        oprot.writeString(struct.orgUser);
      }
      if (struct.isSetOrganization()) {
        struct.organization.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcOrganizationUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.orgUserID = iprot.readString();
        struct.setOrgUserIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.orgUser = iprot.readString();
        struct.setOrgUserIsSet(true);
      }
      if (incoming.get(2)) {
        struct.organization = new RpcOrganization();
        struct.organization.read(iprot);
        struct.setOrganizationIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


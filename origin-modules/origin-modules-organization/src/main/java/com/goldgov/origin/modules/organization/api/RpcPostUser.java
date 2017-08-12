/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.organization.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-08-12")
public class RpcPostUser implements org.apache.thrift.TBase<RpcPostUser, RpcPostUser._Fields>, java.io.Serializable, Cloneable, Comparable<RpcPostUser> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcPostUser");

  private static final org.apache.thrift.protocol.TField POST_USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("postUserID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ORG_USER_FIELD_DESC = new org.apache.thrift.protocol.TField("orgUser", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField ORG_POST_FIELD_DESC = new org.apache.thrift.protocol.TField("orgPost", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcPostUserStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcPostUserTupleSchemeFactory();

  private java.lang.String postUserID; // optional
  private RpcOrganizationUser orgUser; // optional
  private RpcOrganizationPost orgPost; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    POST_USER_ID((short)1, "postUserID"),
    ORG_USER((short)2, "orgUser"),
    ORG_POST((short)3, "orgPost");

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
        case 1: // POST_USER_ID
          return POST_USER_ID;
        case 2: // ORG_USER
          return ORG_USER;
        case 3: // ORG_POST
          return ORG_POST;
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
  private static final _Fields optionals[] = {_Fields.POST_USER_ID,_Fields.ORG_USER,_Fields.ORG_POST};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.POST_USER_ID, new org.apache.thrift.meta_data.FieldMetaData("postUserID", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ORG_USER, new org.apache.thrift.meta_data.FieldMetaData("orgUser", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RpcOrganizationUser.class)));
    tmpMap.put(_Fields.ORG_POST, new org.apache.thrift.meta_data.FieldMetaData("orgPost", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RpcOrganizationPost.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcPostUser.class, metaDataMap);
  }

  public RpcPostUser() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcPostUser(RpcPostUser other) {
    if (other.isSetPostUserID()) {
      this.postUserID = other.postUserID;
    }
    if (other.isSetOrgUser()) {
      this.orgUser = new RpcOrganizationUser(other.orgUser);
    }
    if (other.isSetOrgPost()) {
      this.orgPost = new RpcOrganizationPost(other.orgPost);
    }
  }

  public RpcPostUser deepCopy() {
    return new RpcPostUser(this);
  }

  @Override
  public void clear() {
    this.postUserID = null;
    this.orgUser = null;
    this.orgPost = null;
  }

  public java.lang.String getPostUserID() {
    return this.postUserID;
  }

  public void setPostUserID(java.lang.String postUserID) {
    this.postUserID = postUserID;
  }

  public void unsetPostUserID() {
    this.postUserID = null;
  }

  /** Returns true if field postUserID is set (has been assigned a value) and false otherwise */
  public boolean isSetPostUserID() {
    return this.postUserID != null;
  }

  public void setPostUserIDIsSet(boolean value) {
    if (!value) {
      this.postUserID = null;
    }
  }

  public RpcOrganizationUser getOrgUser() {
    return this.orgUser;
  }

  public void setOrgUser(RpcOrganizationUser orgUser) {
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

  public RpcOrganizationPost getOrgPost() {
    return this.orgPost;
  }

  public void setOrgPost(RpcOrganizationPost orgPost) {
    this.orgPost = orgPost;
  }

  public void unsetOrgPost() {
    this.orgPost = null;
  }

  /** Returns true if field orgPost is set (has been assigned a value) and false otherwise */
  public boolean isSetOrgPost() {
    return this.orgPost != null;
  }

  public void setOrgPostIsSet(boolean value) {
    if (!value) {
      this.orgPost = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case POST_USER_ID:
      if (value == null) {
        unsetPostUserID();
      } else {
        setPostUserID((java.lang.String)value);
      }
      break;

    case ORG_USER:
      if (value == null) {
        unsetOrgUser();
      } else {
        setOrgUser((RpcOrganizationUser)value);
      }
      break;

    case ORG_POST:
      if (value == null) {
        unsetOrgPost();
      } else {
        setOrgPost((RpcOrganizationPost)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case POST_USER_ID:
      return getPostUserID();

    case ORG_USER:
      return getOrgUser();

    case ORG_POST:
      return getOrgPost();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case POST_USER_ID:
      return isSetPostUserID();
    case ORG_USER:
      return isSetOrgUser();
    case ORG_POST:
      return isSetOrgPost();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcPostUser)
      return this.equals((RpcPostUser)that);
    return false;
  }

  public boolean equals(RpcPostUser that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_postUserID = true && this.isSetPostUserID();
    boolean that_present_postUserID = true && that.isSetPostUserID();
    if (this_present_postUserID || that_present_postUserID) {
      if (!(this_present_postUserID && that_present_postUserID))
        return false;
      if (!this.postUserID.equals(that.postUserID))
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

    boolean this_present_orgPost = true && this.isSetOrgPost();
    boolean that_present_orgPost = true && that.isSetOrgPost();
    if (this_present_orgPost || that_present_orgPost) {
      if (!(this_present_orgPost && that_present_orgPost))
        return false;
      if (!this.orgPost.equals(that.orgPost))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetPostUserID()) ? 131071 : 524287);
    if (isSetPostUserID())
      hashCode = hashCode * 8191 + postUserID.hashCode();

    hashCode = hashCode * 8191 + ((isSetOrgUser()) ? 131071 : 524287);
    if (isSetOrgUser())
      hashCode = hashCode * 8191 + orgUser.hashCode();

    hashCode = hashCode * 8191 + ((isSetOrgPost()) ? 131071 : 524287);
    if (isSetOrgPost())
      hashCode = hashCode * 8191 + orgPost.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcPostUser other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetPostUserID()).compareTo(other.isSetPostUserID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPostUserID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.postUserID, other.postUserID);
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
    lastComparison = java.lang.Boolean.valueOf(isSetOrgPost()).compareTo(other.isSetOrgPost());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrgPost()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orgPost, other.orgPost);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcPostUser(");
    boolean first = true;

    if (isSetPostUserID()) {
      sb.append("postUserID:");
      if (this.postUserID == null) {
        sb.append("null");
      } else {
        sb.append(this.postUserID);
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
    if (isSetOrgPost()) {
      if (!first) sb.append(", ");
      sb.append("orgPost:");
      if (this.orgPost == null) {
        sb.append("null");
      } else {
        sb.append(this.orgPost);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (orgUser != null) {
      orgUser.validate();
    }
    if (orgPost != null) {
      orgPost.validate();
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

  private static class RpcPostUserStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcPostUserStandardScheme getScheme() {
      return new RpcPostUserStandardScheme();
    }
  }

  private static class RpcPostUserStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcPostUser> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcPostUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // POST_USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.postUserID = iprot.readString();
              struct.setPostUserIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ORG_USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.orgUser = new RpcOrganizationUser();
              struct.orgUser.read(iprot);
              struct.setOrgUserIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ORG_POST
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.orgPost = new RpcOrganizationPost();
              struct.orgPost.read(iprot);
              struct.setOrgPostIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcPostUser struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.postUserID != null) {
        if (struct.isSetPostUserID()) {
          oprot.writeFieldBegin(POST_USER_ID_FIELD_DESC);
          oprot.writeString(struct.postUserID);
          oprot.writeFieldEnd();
        }
      }
      if (struct.orgUser != null) {
        if (struct.isSetOrgUser()) {
          oprot.writeFieldBegin(ORG_USER_FIELD_DESC);
          struct.orgUser.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.orgPost != null) {
        if (struct.isSetOrgPost()) {
          oprot.writeFieldBegin(ORG_POST_FIELD_DESC);
          struct.orgPost.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcPostUserTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcPostUserTupleScheme getScheme() {
      return new RpcPostUserTupleScheme();
    }
  }

  private static class RpcPostUserTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcPostUser> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcPostUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPostUserID()) {
        optionals.set(0);
      }
      if (struct.isSetOrgUser()) {
        optionals.set(1);
      }
      if (struct.isSetOrgPost()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetPostUserID()) {
        oprot.writeString(struct.postUserID);
      }
      if (struct.isSetOrgUser()) {
        struct.orgUser.write(oprot);
      }
      if (struct.isSetOrgPost()) {
        struct.orgPost.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcPostUser struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.postUserID = iprot.readString();
        struct.setPostUserIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.orgUser = new RpcOrganizationUser();
        struct.orgUser.read(iprot);
        struct.setOrgUserIsSet(true);
      }
      if (incoming.get(2)) {
        struct.orgPost = new RpcOrganizationPost();
        struct.orgPost.read(iprot);
        struct.setOrgPostIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


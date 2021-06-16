package user.model.vo;

import java.sql.Date;

public class CustomerMember {
	// 멤버 변수
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberEmail;
	private String memberAddress;
	private String memberPhone;
	private Date memberEnroll;
	private String memberType;
	private String memberRecieve;

	// 생성자
	public void CustomerMember() {
	}

	// getter/setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Date getMemberEnroll() {
		return memberEnroll;
	}

	public void setMemberEnroll(Date memberEnroll) {
		this.memberEnroll = memberEnroll;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberRecieve() {
		return memberRecieve;
	}

	public void setMemberRecieve(String memberRecieve) {
		this.memberRecieve = memberRecieve;
	}

	// toString
	@Override
	public String toString() {
		return "CustomerMember [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberAddress=" + memberAddress + ", memberPhone=" + memberPhone
				+ ", memberEnroll=" + memberEnroll + ", memberType=" + memberType + ", memberRecieve=" + memberRecieve
				+ "]";
	}

}

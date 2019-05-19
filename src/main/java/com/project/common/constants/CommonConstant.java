package com.project.common.constants;

public class CommonConstant {

	public enum RESPONSE_STATUS {
		SUCCESS, FAILED
	}

	public class RESPONSE_MESSAGE {
		public static final String ERROR500 = "กรุณาติดต่อผู้ดูแลระบบ";
		public static final String SUCCESS = "ทำรายการสำเร็จ";

		public class SAVE {
			public static final String SUCCESS = "บันทึกเรียบร้อยแล้ว";
			public static final String FAILED = "บันทึกไม่สำเร็จ";
		}

		public class DELETE {
			public static final String SUCCESS = "ลบเรียบร้อยแล้ว";
			public static final String FAILED = "ลบไม่สำเร็จ";
		}
	}

	public static final class PETTY_CASH_STATUS {
		public static final String WAIT = "1";
		public static final String APPROVE = "2";
		public static final String NOT_APPROVE = "3";
		public static final String CANCEL = "4";
		public static final String SUCCESS = "5";
	}

	public static final class EMPLOYEE_STATUS {
		public static final String DOING = "1";
		public static final String RESIGN = "2";
	}

	public static final class JOB_LEVEL {
		public static final String JUNIOR = "1";
		public static final String SENIOR = "2";
		public static final String MANAGER = "3";
	}

	public static final class ROLE {
		public static final String NORMAL = "ROLE_NORMAL";
		public static final String MANAGER = "ROLE_MANAMENT";
		public static final String PETTY_CASH = "ROLE_PETTY_CASH";
	}
}

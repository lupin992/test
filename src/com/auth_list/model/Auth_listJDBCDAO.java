package com.auth_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.verify_list.model.Verify_listVO;

public class Auth_listJDBCDAO implements Auth_list_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO auth_list VALUES (?,?)";

	private static final String UPDATE_STMT = "UPDATE auth_list set auth_func=? WHERE auth_id=?";

	private static final String DELETE = "DELETE FROM auth_list where auth_id= ?";

	private static final String GET_ONE_STMT = "SELECT auth_id, auth_func FROM auth_list where auth_id = ?";

	private static final String GET_ALL_STMT = "SELECT auth_id, auth_func FROM auth_list order by auth_id";

	@Override
	public void insert(Auth_listVO auth_listVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setString(1, auth_listVO.getAuth_id());
			pstmt.setString(2, auth_listVO.getAuth_func());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Auth_listVO auth_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, auth_listVO.getAuth_func());
			pstmt.setString(2, auth_listVO.getAuth_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public void delete(String auth_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, auth_listVO);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Auth_listVO findByPrimaryKey(String auth_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Auth_listVO alVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, auth_listVO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				alVO = new Auth_listVO();
				alVO.setAuth_id(rs.getString("auth_id"));
				alVO.setAuth_func(rs.getString("auth_func"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return alVO;
	}

	@Override
	public List<Auth_listVO> getAll() {
		List<Auth_listVO> list = new ArrayList<Auth_listVO>();
		Auth_listVO alVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				alVO = new Auth_listVO();
				alVO.setAuth_id(rs.getString("auth_id"));
				alVO.setAuth_func(rs.getString("Auth_func"));

				list.add(alVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Auth_listJDBCDAO dao = new Auth_listJDBCDAO();

		// �s�W
//		Auth_listVO auth_listVO1 = new Auth_listVO();
//		auth_listVO1.setAuth_id("F");
//		auth_listVO1.setAuth_func("���մ��մ���");
//
//		dao.insert(auth_listVO1);

		// �ק�
//		Auth_listVO auth_listVO2 = new Auth_listVO();
//		auth_listVO2.setAuth_func("�ק�ק�ק�");
//		auth_listVO2.setAuth_id("F");
//
//		dao.update(auth_listVO2);
//
//		// �R��
//		dao.delete("F");
//
//		// �d��
//		Auth_listVO auth_listVO3 = dao.findByPrimaryKey("A");
//		System.out.println(auth_listVO3.getAuth_id() + "�A");
//		System.out.println(auth_listVO3.getAuth_func() + "�C");
//
//		// getAll �d��
//		List<Auth_listVO> list = dao.getAll();
//		for (Auth_listVO alVO : list) {
//			System.out.println(alVO.getAuth_id() + "�A");
//			System.out.println(alVO.getAuth_func() + "�C");
//
//			System.out.println();
//		}

	}

}

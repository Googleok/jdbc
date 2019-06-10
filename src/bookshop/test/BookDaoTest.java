package bookshop.test;

import java.util.List;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		insert("아리랑", 3L);
		insert("젊은그들", 4L);
		insert("아프니깐 청춘이다", 5L);
		insert("귀천", 6L);
		insert("태백산맥", 6L);
		insert("풀하우스", 6L);
//		update(1L, "대여가능");
		getListTest();
	}

	public static void insert(String title, Long authorNo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setAuthorNo(authorNo);
		
		new BookDao().insert(vo);
		
	}
	
	public static void update(Long no, String status) {
		new BookDao().update(no, status);
	}
	
	
	
	public static void getListTest() {
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}

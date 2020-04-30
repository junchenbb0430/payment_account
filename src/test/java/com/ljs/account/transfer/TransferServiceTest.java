/**
 * 
 */
package com.ljs.account.transfer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.ljs.account.PaymentAccountApplicationTests;
import com.ljs.account.mock.module.account.AccountServiceImpl;
import com.ljs.account.mock.module.account.EgfAccount;
import com.ljs.account.mock.module.card.DebtCardServiceImpl;
import com.ljs.account.mock.module.response.AccountResp;
import com.ljs.account.mock.module.response.DebtCardInfo;
import com.ljs.account.mock.module.response.TransferResult;
import com.ljs.account.mock.trans.CardTransferService;
/**
 * @author lijunshi
 *
 */
public class TransferServiceTest extends PaymentAccountApplicationTests {
	@Autowired
	private CardTransferService transferService;
	
	
	private DebtCardServiceImpl  mockCardService;
	 
	private AccountServiceImpl  mockAcctService;
	
	
	/**
	 * 正向案例
	 */
	@Test
	public void  testTransferSuccess() {
		 
		TransferResult result = transferService.transfer("6226220104832852","6226220104832853",new BigDecimal("100.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	}
	
	/**
	 * 模拟金额超限失败
	 */
	@Test
	public void testTransferOfAmountFailure() {
		TransferResult result = transferService.transfer("6226220104832852","6226220104832853",new BigDecimal("600.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	}
	
	/**
	 * 模拟付款卡号失败场景
	 */
	@Test
	public void testTransferOfPayCardFailure() {
		TransferResult result = transferService.transfer("6226220104832854","6226220104832855",new BigDecimal("100.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	}
	
	/**
	 * 模拟收款卡号失败场景
	 */
	@Test
	public void testTransferOfPayeeCardFailure() {
		TransferResult result = transferService.transfer("6226220104832852","6226220104832856",new BigDecimal("100.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	}
	
//	@Before
//	public void setup() {
//		mockCardService = mock(DebtCardServiceImpl.class);
//		 
//		when(mockCardService.queryCardInfoByCardNo("5555555555555","out")).thenReturn(new DebtCardInfo("1111111","5555555555555","99999999","1","赵四") );
//		 
//		when(mockCardService.queryCardInfoByCardNo("6226234234234","in")).thenReturn(new DebtCardInfo("2222222","6226234234234","888888888","1","刘能"));
//		 
//	}
	
	/**
	 * 模拟卡号有问题，转账成功的场景
	 */
	@Test
	public void testMockTransferOfSuccess() {
		mockCardService = mock(DebtCardServiceImpl.class);
		 
		when(mockCardService.queryCardInfoByCardNo("9226220104832852" )).thenReturn(new DebtCardInfo("000002","9226220104832852","1111111111111","1","赵四11") );
		 
		when(mockCardService.queryCardInfoByCardNo("8226220104832853" )).thenReturn(new DebtCardInfo("000003","8226220104832853","2222222222222","1","刘能1"));
		 
		transferService.setCardService(mockCardService); 
		
		TransferResult result = transferService.transfer("9226220104832852","8226220104832853",new BigDecimal("100.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	}
	
	
	
	
	/**
	 * 模拟账务服务和卡服务都成功
	 */
	@Test
	public void testMockTransferPayerSuccess() {
		mockCardService = mock(DebtCardServiceImpl.class);
		DebtCardInfo fromCard = new DebtCardInfo("000002","7226220104832852","1111101111111","1","赵四1");
		when(mockCardService.queryCardInfoByCardNo("7226220104832852" )).thenReturn(fromCard );
		
		DebtCardInfo toCard = new DebtCardInfo("000003","8226220104832853","2222220222222","2","刘能2");
		when(mockCardService.queryCardInfoByCardNo("8226220104832853" )).thenReturn(toCard);
		 
		transferService.setCardService(mockCardService); 
		
		  mockAcctService = mock(AccountServiceImpl.class);
		 
		  when(mockAcctService.transfer(fromCard.getMasterAcctNo(),fromCard.getAcctName(),
				  	toCard.getMasterAcctNo(),toCard.getAcctName(), new BigDecimal("100.00"))).thenReturn(new AccountResp("200","成功"));
		  transferService.setAcctService(mockAcctService);
		  TransferResult result = transferService.transfer("7226220104832852","8226220104832853",new BigDecimal("100.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	}
	
	@Test
	public void  testMockTransferFailureOfAcctName() {
		mockCardService = mock(DebtCardServiceImpl.class);
		 
		when(mockCardService.queryCardInfoByCardNo("7777777777777" )).thenReturn(new DebtCardInfo("1111111","7777777777777","99999999","1","王五") );
		 
		when(mockCardService.queryCardInfoByCardNo("6226234234234" )).thenReturn(new DebtCardInfo("2222222","6226234234234","888888888","1","刘能"));
		transferService.setCardService(mockCardService); 
		
		TransferResult result = transferService.transfer("7777777777777","6226234234234",new BigDecimal("100.00"));
		System.out.println("转账结果 是 ：  "+result.getCode()+"----->"+result.getInfo());
		assertEquals("200",result.getCode());
	} 
}

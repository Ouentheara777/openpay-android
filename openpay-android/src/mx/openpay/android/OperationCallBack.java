/*
 * COPYRIGHT © 2012-2014. OPENPAY.
 * PATENT PENDING. ALL RIGHTS RESERVED.
 * OPENPAY & OPENCARD IS A REGISTERED TRADEMARK OF OPENCARD INC.
 *
 * This software is confidential and proprietary information of OPENCARD INC.
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the company policy.
 */
package mx.openpay.android;

import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;

/**
 * The Interface OperationCallBack.
 */
public interface OperationCallBack {

	/**
	 * On error.
	 *
	 * @param error the error
	 */
	void onError(OpenpayServiceException error);
	
	/**
	 * On communication error.
	 *
	 * @param error the error
	 */
	void onCommunicationError(ServiceUnavailableException error);
    
    /**
     * On success.
     *
     * @param operationResult the operation result
     */
    void onSuccess(OperationResult operationResult);
}

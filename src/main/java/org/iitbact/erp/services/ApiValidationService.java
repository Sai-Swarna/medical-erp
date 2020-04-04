package org.iitbact.erp.services;

import org.iitbact.erp.exceptions.HospitalErpErrorCode;
import org.iitbact.erp.exceptions.HospitalErpException;
import org.iitbact.erp.exceptions.HosptialErpErrorMsg;
import org.iitbact.erp.requests.BaseRequest;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class ApiValidationService {

	public String verifyFirebaseIdToken(BaseRequest request) {

		// idToken comes from the client app
		try {
			FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(request.getAuthToken());
			String uid = decodedToken.getUid();
			return uid;
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
			throw new HospitalErpException(HospitalErpErrorCode.INVALID_ACCESS_CODE, HosptialErpErrorMsg.INVALID_ACCESS_CODE, e);
		}

	}

}

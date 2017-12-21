/*
 * Copyright 2013 Opencard Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.openpay.android.test;

import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;
import mx.openpay.android.model.Address;
import mx.openpay.android.model.Card;
import mx.openpay.android.model.Token;
import mx.openpay.android.services.ServicesFactory;
import mx.openpay.android.services.TokenService;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Luis Delucio
 */
public class TokenServicesTest {

    private static final String URL_SANDBOX = "https://sandbox-api.openpay.mx";
    private TokenService tokenService;

    @Before
    public void setUp() throws Exception {
        this.tokenService = ServicesFactory.getInstance(URL_SANDBOX, "mi93pk0cjumoraf08tqt",
                "pk_92e31f7c77424179b7cd451d21fbb771").getService(TokenService.class);
    }

    @Test
    public void createMerchantCard() throws OpenpayServiceException, ServiceUnavailableException {

        Token newCard = this.tokenService.create(this.getCard());

        assertNotNull(newCard);
        assertNotNull(newCard.getCard());
    }

    @Test
    public void createMerchantCardWithAddress() throws OpenpayServiceException, ServiceUnavailableException {

        Card card = this.getCard();
        card.address(this.getAddres());
        Token newCard = this.tokenService.create(card);

        assertNotNull(newCard);
        assertNotNull(newCard.getCard());
        assertNotNull(newCard.getCard().getAddress());
    }

    @Test
    public void createMerchantCard_ErrorDataCard() throws ServiceUnavailableException {
        Card card = this.getCard();
        card.expirationYear(12);
        try {
            this.tokenService.create(card);
        } catch (OpenpayServiceException e) {
            assertNotNull(e.getDescription());
            assertNotNull(e.getRequestId());
            assertNotNull(e.getCategory());
            assertNotNull(e.getHttpCode());
            assertNotNull(e.getErrorCode());
        }
    }

    private Address getAddres() {
        return new Address().city("Mexico").countryCode("MX").line1("Calle de la felicidad").line2("Numero 20")
                .line3("Col ensueño").postalCode("76900").state("Mexico");
    }

    private Card getCard() {
        Card card = new Card().cardNumber("4111111111111111").holderName("Pedro Paramo").expirationMonth(2)
                .expirationYear(19).cvv2("123");
        return card;
    }

}

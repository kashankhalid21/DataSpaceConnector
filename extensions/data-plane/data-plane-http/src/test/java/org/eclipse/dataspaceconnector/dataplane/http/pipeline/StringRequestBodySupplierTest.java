/*
 *  Copyright (c) 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.dataplane.http.pipeline;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StringRequestBodySupplierTest {

    private static final Faker FAKER = new Faker();

    @Test
    void verifyExceptionThrownIfNullBody() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new StringRequestBodySupplier(null));
    }

    @Test
    void verifySuccessSupply() throws IOException {
        var body = FAKER.lorem().word();
        var supplier = new StringRequestBodySupplier(body);

        try (var is = supplier.get()) {
            assertThat(new String(is.readAllBytes())).isEqualTo(body);
        }
    }
}
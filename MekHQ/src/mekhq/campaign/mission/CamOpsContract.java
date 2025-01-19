/*
 * Copyright (c) 2025 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MekHQ.
 *
 * MekHQ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MekHQ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MekHQ. If not, see <http://www.gnu.org/licenses/>.
 */
package mekhq.campaign.mission;

import megamek.logging.MMLogger;
import mekhq.campaign.Campaign;
import mekhq.campaign.finances.Accountant;
import mekhq.campaign.finances.Money;

public class CamOpsContract extends AtBContract {
    private static final MMLogger logger = MMLogger.create(CamOpsContract.class);

    public CamOpsContract(String name) { super(name); }

    @Override
    public void calculateContract(Campaign campaign) {
        Accountant accountant = campaign.getAccountant();
        setBaseAmount(calculateBaseAmount(campaign));
    }

    private Money calculateBaseAmount(Campaign campaign) {
        Accountant accountant = campaign.getAccountant();
        Money payment = accountant.getPeacetimeCost().multipliedBy(0.75);
        Money forceValue = accountant.getForceValue(campaign.getCampaignOptions().isInfantryDontCount(), true);
        payment.plus(forceValue.multipliedBy(0.05));
        return payment;
    }
}

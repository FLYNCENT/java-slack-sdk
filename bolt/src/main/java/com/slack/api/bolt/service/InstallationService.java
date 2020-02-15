package com.slack.api.bolt.service;

import com.slack.api.bolt.model.Bot;
import com.slack.api.bolt.model.Installer;

public interface InstallationService {

    boolean isHistoricalDataEnabled();

    void setHistoricalDataEnabled(boolean isHistoricalDataEnabled);

    void saveInstallerAndBot(Installer installer) throws Exception;

    void deleteBot(Bot bot) throws Exception;

    void deleteInstaller(Installer installer) throws Exception;

    Bot findBot(String enterpriseId, String teamId);

    Installer findInstaller(String enterpriseId, String teamId, String userId);

}

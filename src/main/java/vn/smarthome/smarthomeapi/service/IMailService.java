package vn.smarthome.smarthomeapi.service;

import vn.smarthome.smarthomeapi.model.MailModel;

public interface IMailService {
    public void sendEmail(MailModel mail);
}

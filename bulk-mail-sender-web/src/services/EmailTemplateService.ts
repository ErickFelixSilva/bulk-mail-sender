import axios, { AxiosRequestConfig } from 'axios';

export type EmailTemplate = {
  id?: number;
  subject: string;
  body: string;
}

const API_URL = 'http://localhost:8080/api/email-template';

const getEmailTemplate = async (config?: AxiosRequestConfig): Promise<EmailTemplate> => {
  const response = await axios.get<EmailTemplate>(API_URL, config);
  return response.data;
};

const saveEmailTemplate = async (template: EmailTemplate, config?: AxiosRequestConfig): Promise<EmailTemplate> => {
  const response = await axios.post<EmailTemplate>(API_URL, template, config);
  return response.data;
};

export { getEmailTemplate, saveEmailTemplate };

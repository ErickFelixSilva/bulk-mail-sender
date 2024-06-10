import axios, { AxiosRequestConfig } from 'axios';

export type Nonprofit = {
  id?: number;
  name: string;
  email: string;
  address: string;
  recentlySent: boolean;
}

const API_URL = 'http://localhost:8080/api/nonprofits';

const getNonprofits = async (config?: AxiosRequestConfig): Promise<Nonprofit[]> => {
  const response = await axios.get<Nonprofit[]>(API_URL, config);
  return response.data;
};

const saveNonprofit = async (nonprofit: Nonprofit, config?: AxiosRequestConfig): Promise<Nonprofit> => {
  const response = await axios.post<Nonprofit>(API_URL, nonprofit, config);
  return response.data;
};

const updateNonprofit = async (nonprofit: Nonprofit, config?: AxiosRequestConfig): Promise<Nonprofit> => {
  const response = await axios.put<Nonprofit>(`${API_URL}/${nonprofit.id}`, nonprofit, config);
  return response.data;
};
const deleteNonprofit = async (id: number, config?: AxiosRequestConfig): Promise<void> => {
  await axios.delete(`${API_URL}/${id}`, config);
};

export { getNonprofits, saveNonprofit, updateNonprofit, deleteNonprofit };

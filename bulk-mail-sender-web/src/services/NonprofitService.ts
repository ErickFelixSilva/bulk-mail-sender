import axios from 'axios';

export type Nonprofit = {
  id?: number;
  name: string;
  email: string;
  address: string;
}

const API_URL = 'http://localhost:8080/api/nonprofits';

const getNonprofits = async (): Promise<Nonprofit[]> => {
  const response = await axios.get<Nonprofit[]>(API_URL);
  return response.data;
};

const saveNonprofit = async (nonprofit: Nonprofit): Promise<Nonprofit> => {
  const response = await axios.post<Nonprofit>(API_URL, nonprofit);
  return response.data;
};

const updateNonprofit = async (nonprofit: Nonprofit): Promise<Nonprofit> => {
  const response = await axios.put<Nonprofit>(`${API_URL}/${nonprofit.id}`, nonprofit);
  return response.data;
};
const deleteNonprofit = async (id: number): Promise<void> => {
  await axios.delete(`${API_URL}/${id}`);
};

export { getNonprofits, saveNonprofit, updateNonprofit, deleteNonprofit };
